package com.ximu.init.exception;

import com.ximu.init.common.ResultEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: 析木
 * @description: 异常抛出工具类
 */
@Slf4j
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            log.info(runtimeException.getMessage());
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     */
    public static void throwIf(boolean condition, ResultEnum resultEnum) {
        throwIf(condition, new CustomException(resultEnum));
    }
}
