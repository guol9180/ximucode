package com.ximu.init.model.dto.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 析木
 * @description: 用户信息更新请求
 */
@Data
public class UserUpdateDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 简介
     */
    private String profile;

    /**
     * 用户角色：user/admin/disabled
     */
    private String userRole;
}
