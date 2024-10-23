package com.ximu.init.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ximu.init.model.entity.User;

/**
 * @author: 析木
 * @description: 用户服务
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param username   用户账户
     * @param password  用户密码
     * @param checkPassword 校验密码
     */
    void register(String username, String password, String checkPassword);

    String login(String username, String password);
}
