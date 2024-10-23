package com.ximu.init.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: 析木
 * @description: 用户视图（脱敏）
 */
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户介绍
     */
    private String profile;

    /**
     * 用户角色（user/admin/disabled）
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;

}
