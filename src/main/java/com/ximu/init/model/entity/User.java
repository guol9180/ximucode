package com.ximu.init.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.ximu.init.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: 析木
 * @description: 用户实体类
 */

@Data
@EqualsAndHashCode(callSuper = true) // 确保父类的属性也被考虑在内。
public class User extends BaseEntity {

    /**
     * 用户账号
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 微信开放平台 id
     */
    @TableField("union_Id")
    private String unionId;

    /**
     * 微信公众号 openId
     */
    @TableField("open_Id")
    private String openId;

    /**
     * 用户昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 用户头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 用户介绍
     */
    @TableField("profile")
    private String profile;

    /**
     * 用户角色（user/admin/disabled）
     */
    @TableField("user_role")
    private String userRole;

}
