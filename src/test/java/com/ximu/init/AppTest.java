package com.ximu.init;

import com.ximu.init.model.entity.User;
import com.ximu.init.model.enums.UserRoleEnum;
import com.ximu.init.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author: 析木
 * @description: TODO
 */
@SpringBootTest
public class AppTest {

    @Test
    public void createTokenTest() {
        HashMap<String, Object> map = new HashMap<>();
        String username = "ximu";
        Long id = 1L;
        String role = UserRoleEnum.ADMIN.getCode();
        map.put("id", id);
        map.put("username", username);
        map.put("role", role);
        String token = JwtUtil.createToken(map, username);
        System.out.println(token);
    }

    @Test
    public void pareseTokenTest() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlclJvbGUiOiJ1c2VyIiwiZXhwIjoxNzI5NjUwMTIzLCJpYXQiOjE3Mjk2NDY1MjMsInVzZXJuYW1lIjoieGltdSJ9.f4d44U1WHky1h2xvx47vVE2DBH0uUpxreLi0ffU63mE";
        Claims claims = JwtUtil.paresToken(token);
        System.out.println(claims);
    }
}
