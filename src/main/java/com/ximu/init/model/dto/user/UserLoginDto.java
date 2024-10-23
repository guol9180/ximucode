package com.ximu.init.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 析木
 * @description: 用户信息登录请求
 */
@Data
public class UserLoginDto implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 用户账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
