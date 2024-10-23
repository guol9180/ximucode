package com.ximu.init.config.mvc;

import com.ximu.init.common.BaseEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * @author: 析木
 * @description:  实现将 string 转换成 BaseEnum下任意子类型。
 * <p>
 *  默认情况下，MyBatis 使用枚举常量的名字来进行转换。
 *      因此，String 转换 枚举 时，会将枚举的常量名字作为字符串值，
 *      但我们需要的是枚举常量对应的 code值。所以，为了应对此问题，
 *      我们可以自定义一个 ConverterFactory，
 *      并结合在需要映射的属性上添加 @EnumValue 注解来实现此效果。
 * <p>
 *  在 Spring MVC 中，若使用了 @RequestBody注解，同样存在此类问题。
 *      同理，在 枚举 类型转换成 JSON String 时，可以使用 @JsonValue 注解来实现正确的序列化。
 */
@Component
public class StringToBaseEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

    @NotNull
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(@NotNull Class<T> targetType) {
        return code -> {
            // 根据目标类型类对象获取所有枚举类型
            T[] enumConstants = targetType.getEnumConstants();
            for (T enumConstant : enumConstants) {
                if (enumConstant.getCode().equals(Integer.valueOf(code))) {
                    return enumConstant;
                }
            }
            throw new IllegalArgumentException("非法的枚举值：" + code);
        };
    }
}
