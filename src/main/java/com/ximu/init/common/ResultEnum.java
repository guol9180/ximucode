package com.ximu.init.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: 析木
 * @description: 全局通用返回结果状态码
 *  Web状态码主要分为以下几类：
 *      1xx 信息性状态码‌：表示请求已接收，继续处理；           200 表示成功 304 表示未修改
 *      2xx 成功状态码‌：表示请求已成功接收、理解、并接受；      400 表示请求有语法错误
 *      3xx 重定向状态码‌：表示需要进一步操作以完成请求；        401 表示请求未经授权
 *      4xx 客户端错误‌：表示客户端请求有语法错误或请求无法完成；  403 表示请求被拒绝
 *      5xx 服务器错误‌：表示服务器无法完成请求。               404 表示请求资源不存在
 *                                                      500 表示服务器内部错误
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200, "成功"),

    USERNAME_OR_PASSWORD_NOT_MATCH(30401, "用户名或密码错误"),
    PASSWORD_NOT_SAME(30402, "两次输入的密码不一致"),
    NICKNAME_EXIST(30403, "昵称已被占用"),
    USERNAME_EXIST(30404, "用户名已注册"),
    USERNAME_TOO_SHORT(30405, "用户名过短"),
    PASSWORD_TOO_SHORT(30406, "密码过短"),

    BAD_REQUEST(400, "非法请求"),

    NO_AUTH(401, "无授权"),
    NO_LOGIN(40101, "没有登录信息，请先登录"),
    TOKEN_EXPIRED(40102, "token已过期"),
    TOKEN_INVALID(40103, "token已失效"),

    FORBIDDEN(403, "禁止访问"),
    USERNAME_DISABLED(40301, "该用户已被禁用"),
    REPEAT_SUBMIT(40302, "重复提交"),

    NOT_FOUND(404, "未找到资源"),

    SERVICE_ERROR(500, "服务异常"),
    DATABASE_SERVICE_ERROR(50001, "注册失败，数据库服务异常");

    /**
     * 返回状态码
     */
    private final Integer code;

    /**
     * 返回数据
     */
    private final String message;

}
