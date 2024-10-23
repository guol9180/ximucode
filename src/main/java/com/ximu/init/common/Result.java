package com.ximu.init.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: 析木
 * @param <T>
 * @description: 全局通用响应结果类
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回消息
     */
    private String message;

    /**
     * @author: 析木
     * @description: 构建返回结果对象，设置返回数据
     * @param: <T>
     * @return: Result<T>
     */
    public static <T> Result<T> build(T data) {
        Result<T> result = new Result<>();
        if (data != null)
            result.setData(data);
        return result;
    }

    /**
     * @author: 析木
     * @description: 构建返回结果对象，设置返回数据、状态码、信息
     * @param: data, resultEnum
     * @return: Result<T>
     */
    public static <T> Result<T> build(T data, ResultEnum resultEnum) {
        Result<T> result = build(data);
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        return result;
    }

    /**
     * @author: 析木
     * @description: 成功，设置响应数据，返回默认信息
     * @param: <T>
     * @return: Result<T>
     */
    public static <T> Result<T> ok(T data) {
        return build(data, ResultEnum.SUCCESS);
    }

    /**
     * @author: 析木
     * @description: 成功，无响应数据
     * @param: null
     * @return: Result<T>
     */
    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    /**
     * @author: 析木
     * @description: 失败，返回默认信息
     * @param: null
     * @return: Result<T>
     */
    public static <T> Result<T> fail() {
        return build(null, ResultEnum.SERVICE_ERROR);
    }


    /**
     * @author: 析木
     * @description: 失败，设置响应信息，返回失败信息
     * @param: code, message
     * @return: Result<T>
     */
    public static <T> Result<T> fail(int code, String message) {
        Result<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
