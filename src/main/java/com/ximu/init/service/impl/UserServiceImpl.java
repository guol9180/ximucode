package com.ximu.init.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ximu.init.common.ResultEnum;
import com.ximu.init.constant.CommonConstant;
import com.ximu.init.exception.ThrowUtils;
import com.ximu.init.mapper.UserMapper;
import com.ximu.init.model.entity.User;
import com.ximu.init.model.enums.UserRoleEnum;
import com.ximu.init.service.UserService;
import com.ximu.init.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;

/**
 * @author: 析木
 * @description: 用户服务实现
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void register(String username, String password, String checkPassword) {

        // 1.参数校验
        ThrowUtils.throwIf(username.length() < 4, ResultEnum.USERNAME_TOO_SHORT);
        ThrowUtils.throwIf(password.length() < 6 || checkPassword.length() < 6, ResultEnum.PASSWORD_TOO_SHORT);
        ThrowUtils.throwIf(!password.equals(checkPassword), ResultEnum.PASSWORD_NOT_SAME);

        // 2.业务处理
        // 调用 intern() 方法时，如果常量池中已经存在一个与该字符串内容相同的字符串，则返回常量池中的那个字符串的引用；
        // 如果不存在，则将该字符串添加到常量池中，并返回它的引用。 确保对同一个 username 的注册操作是线程安全的。
        synchronized (username.intern()) {
            // 判断用户名是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            Long count = this.baseMapper.selectCount(queryWrapper);
            ThrowUtils.throwIf(count > 0, ResultEnum.USERNAME_EXIST);

            // 密码加密
            String encryptPassword = DigestUtils.md5DigestAsHex((CommonConstant.SALT + password).getBytes());
            // 保存数据
            User user = new User();
            user.setUsername(username);
            user.setPassword(encryptPassword);
            user.setAvatar(CommonConstant.DEFAULT_AVATAR);
            boolean isSaved = this.save(user);
            ThrowUtils.throwIf(!isSaved, ResultEnum.DATABASE_SERVICE_ERROR);
        }
    }

    @Override
    public String login(String username, String password) {

        // 1.参数校验
        ThrowUtils.throwIf(username.length() < 4, ResultEnum.USERNAME_TOO_SHORT);
        ThrowUtils.throwIf(password.length() < 6, ResultEnum.PASSWORD_TOO_SHORT);

        // 2.密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((CommonConstant.SALT + password).getBytes());

        // 3.查询用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        queryWrapper.eq("password", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);

        // 4.判断账号是否可用
        ThrowUtils.throwIf(user == null, ResultEnum.USERNAME_OR_PASSWORD_NOT_MATCH);
        ThrowUtils.throwIf(UserRoleEnum.DISABLED.getCode().equals(user.getUserRole()), ResultEnum.USERNAME_DISABLED);

        // 5.封装信息，生成 token
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("userRole", user.getUserRole());
        return JwtUtil.createToken(map, username);
    }

}
