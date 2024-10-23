package com.ximu.init.controller;

import com.ximu.init.common.Result;
import com.ximu.init.common.ResultEnum;
import com.ximu.init.exception.ThrowUtils;
import com.ximu.init.model.dto.user.UserLoginDto;
import com.ximu.init.model.dto.user.UserRegisterDto;
import com.ximu.init.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 析木
 * @description: 用户接口
 */
@Api(value = "用户接口", tags = {"用户"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author: 析木
     * @description: 用户注册
     * @params: userRegisterDto
     * @return: Result
     */
    @ApiOperation(value = "用户注册", notes = "根据账号密码注册")
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        // 检查 userRegisterDto 是否为 null
        ThrowUtils.throwIf(userRegisterDto == null, ResultEnum.BAD_REQUEST);

        String username = userRegisterDto.getUsername();
        String password = userRegisterDto.getPassword();
        String checkPassword = userRegisterDto.getCheckPassword();
        // 检查 username/password/checkPassword 是否为 null
        ThrowUtils.throwIf(StringUtils.isAnyBlank(username, password, checkPassword)
                , ResultEnum.BAD_REQUEST);
        userService.register(username, password, checkPassword);
        return Result.ok();
    }

    /**
     * @author: 析木
     * @description: 用户注册
     * @params: userRegisterDto
     * @return: String
     */
    @ApiOperation(value = "用户登录", notes = "根据账号密码登录")
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginDto userLoginDto) {
        // 检查 userLoginDto 是否为 null
        ThrowUtils.throwIf(userLoginDto == null, ResultEnum.BAD_REQUEST);

        String username = userLoginDto.getUsername();
        String password = userLoginDto.getPassword();
        // 检查 username/password 是否为 null
        ThrowUtils.throwIf(StringUtils.isAnyBlank(username, password)
                , ResultEnum.BAD_REQUEST);
        String token = userService.login(username, password);
        return Result.ok(token);
    }
}
