package com.ximu.init.utils;

import com.ximu.init.common.ResultEnum;
import com.ximu.init.constant.CommonConstant;
import com.ximu.init.exception.CustomException;
import com.ximu.init.exception.ThrowUtils;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 析木
 * @description: JSON web token 工具类
 */
public class JwtUtil {

    /**
     * @author: 析木
     * @description: 传入需要保存的信息，生成 token 字符串
     * @params: claims，username
     * @return: String
     */
    public static String createToken(Map<String, Object> claims, String username) {
        // 1.获取 token过期时间
        Date now = new Date(System.currentTimeMillis());
        Date expiryDate = new Date(now.getTime() + CommonConstant.TOKEN_EXPIRE_TIME);

        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 2.构建 token
        return Jwts.builder()
                .setHeader(header)                  // 第一部分：header
                .setSubject(username)               // 第二部分：payload，令牌主题
                .setClaims(claims)                  // 第二部分：payload，自定义声明内容
                .setIssuedAt(now)                   // 第二部分：payload，签发时间
                .setExpiration(expiryDate)          // 第二部分：payload，过期时间
                .signWith(SignatureAlgorithm.HS256, CommonConstant.TOKEN_SIGN_KEY) // 第三部分：Signature，使用指定的算法和秘钥，将头部和有效载荷进行加密
                .compact();                         // 调用方法，生成完整 token
    }

    public static Claims paresToken(String token) {
        // 是否登录
        ThrowUtils.throwIf(token == null, ResultEnum.NO_LOGIN);
        try {
            // 解密
            JwtParser jwtParser = Jwts.parser().setSigningKey(CommonConstant.TOKEN_SIGN_KEY);
            // 获取声明的内容
            Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ResultEnum.TOKEN_EXPIRED); // 是否过期
        } catch (JwtException e) {
            throw new CustomException(ResultEnum.TOKEN_INVALID); // 是否有效
        }
    }
}
