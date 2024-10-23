package com.ximu.init.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 析木
 * @description: 用户角色枚举
 */
@Getter
@AllArgsConstructor
public enum UserRoleEnum {

    USER("user","用户"),
    ADMIN("admin","管理员"),
    DISABLED("disabled","禁用");

    private final String code;

    private final String text;

    /**
     * 根据 value 获取枚举
     *
     * @return UserRoleEnum
     */
    public static UserRoleEnum getEnumByCode(String code) {
        if (ObjectUtils.isEmpty(code)) {
            return null;
        }
        for (UserRoleEnum anEnum : UserRoleEnum.values()) {
            if (anEnum.code.equals(code)) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * 获取值列表
     *
     * @return List
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.code).collect(Collectors.toList());
    }

}
