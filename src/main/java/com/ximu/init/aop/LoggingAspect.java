package com.ximu.init.aop;

import com.ximu.init.auth.UserContext;
import com.ximu.init.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author: 析木
 * @description: 响应式日志 AOP
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.ximu.init.controller..*(..))")
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {

        // 1.计时
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // 2.获取当前请求的 HttpServletRequest
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        /// 3.生成请求唯一 id，获取请求路径
        String requestId = UUID.randomUUID().toString();
        String url = request.getRequestURI();

        // 4.获取请求参数
        Object[] args = joinPoint.getArgs();
        String reqParam = "[" + StringUtils.join(args, ", ") + "]";

        // 5.获取用户信息
        User user = UserContext.getUserInfo();

        // 6.输出请求日志
        log.info("request start, id: {}, path: {}, ip: {}, params: {}"
                , requestId, url,request.getRemoteHost(), reqParam);

        // 7.执行原方法
        Object result = joinPoint.proceed();

        // 8.输出响应日志
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        log.info("request end, id: {}, cost: {}ms", requestId, totalTimeMillis);
        return result;
    }
}
