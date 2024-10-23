package com.ximu.init.aop;

import com.ximu.init.annotation.AuthCheck;
import com.ximu.init.auth.UserContext;
import com.ximu.init.common.ResultEnum;
import com.ximu.init.exception.ThrowUtils;
import com.ximu.init.model.entity.User;
import com.ximu.init.model.enums.UserRoleEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author: 析木
 * @description: 权限校验 AOP
 */
@Aspect
@Component
public class AuthAspect {

    /**
     * 执行拦截
     */
    @Around("@annotation(authCheck)")
    public Object checkAuth(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {

        // 1.获取方法上的注解权限
        String mustRole = authCheck.mustRole();
        UserRoleEnum mustRoleEnum = UserRoleEnum.getEnumByCode(mustRole);

        // 2.从 ThreadLocal 中获取用户角色
        User user = UserContext.getUserInfo();

        // 3.不需要权限，放行
        if (mustRoleEnum == null) {
            return joinPoint.proceed();
        }

        // 4.获取用户的权限，必须有该权限才通过
        UserRoleEnum userRoleEnum = UserRoleEnum.getEnumByCode(user.getUserRole());
        // 没有权限
        ThrowUtils.throwIf(userRoleEnum == null, ResultEnum.NO_AUTH);
        // 用户被禁用
        ThrowUtils.throwIf(UserRoleEnum.DISABLED.equals(userRoleEnum), ResultEnum.USERNAME_DISABLED);
        // 必须是管理员权限
        if (UserRoleEnum.ADMIN.equals(mustRoleEnum)) {
            ThrowUtils.throwIf(!UserRoleEnum.ADMIN.equals(userRoleEnum), ResultEnum.NO_AUTH);
        }
        // 5.通过权限校验，放行
        return joinPoint.proceed();
    }
}
