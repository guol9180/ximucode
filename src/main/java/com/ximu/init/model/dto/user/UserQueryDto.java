package com.ximu.init.model.dto.user;

import com.ximu.init.common.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author: 析木
 * @description: 用户信息查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryDto extends PageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 开放平台id
     */
    private String unionId;

    /**
     * 公众号openId
     */
    private String openId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 简介
     */
    private String profile;

    /**
     * 用户角色：user/admin/disabled
     */
    private String userRole;
}
