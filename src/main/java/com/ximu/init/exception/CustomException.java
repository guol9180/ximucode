package com.ximu.init.exception;

import com.ximu.init.common.ResultEnum;
import lombok.Getter;

/**
 * @author: 析木
 * @description: 自定义运行时异常，根据传入信息抛出信息
 */
@Getter
public class CustomException extends RuntimeException {


    /**
     * 状态码
     */
    private final Integer code;

    public CustomException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    // 为了提高代码的简洁性，提供一传入枚举对象的构造方法
    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
