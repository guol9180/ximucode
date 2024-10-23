package com.ximu.init.exception;

import com.ximu.init.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: 析木
 * @description: 全局异常处理器
 *      SpringMVC 提供的全局异常捕获功能，通过捕获各种异常并做出相应处理
 *         使用 @ResponseBody注解将捕获异常后做出的处理作为请求的结果返回
 *      @RestControllerAdvice: 用于处理全局异常和统一返回结果
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public Result<?> customExceptionHandler(CustomException e) {
        log.error("CustomException", e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return Result.fail();
    }
}
