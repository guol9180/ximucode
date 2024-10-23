package com.ximu.init.auth;

import com.ximu.init.model.entity.User;
import com.ximu.init.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 析木
 * @description: 配置请求前置拦截器，获取 token并验证
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        // 1.从请求头里获取 token
        String token = request.getHeader("accesstoken");

        // 2.解密获取信息
        Claims claims = JwtUtil.paresToken(token);
        User user = new User();
        user.setUsername(claims.get("username", String.class));
        user.setId(claims.get("id", Long.class));
        user.setUserRole(claims.get("userRole", String.class));

        // 3.存储用户信息到 threadlocal
        UserContext.setUserInfo(user);

        return true;
    }

    @Override // 因为 Thredlocal对于同一个线程是共享数据的，因此需要每次清理用户信息，否则信息会错乱
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 清理 ThreadLocal 中的用户信息
        UserContext.clear();
    }
}
