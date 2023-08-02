package com.zhong.enums;


import java.util.Arrays;
import java.util.Objects;

/**
 *
 */
public interface IEnum<I, S> {

    I getValue();

    S getLabel();

    /**
     * 通过枚举值获取对应的标签名称，适用于返回名称给前端
     */
    static <T extends IEnum<?, ?>> String getLabelByValue(Class<T> clazz, Integer value) {
        T[] constants = clazz.getEnumConstants();
        return Arrays.stream(constants)
                .filter(constant -> constant.getValue().equals(value))
                .findFirst()
                .map(constant -> constant.getLabel().toString())
                .orElse("");
    }

    /**
     * 通过枚举获取对应的值
     */
    static <T extends IEnum<Integer, ?>> Integer getValueByLabel(Class<T> clazz, String label) {
        T[] constants = clazz.getEnumConstants();
        return Arrays.stream(constants)
                .filter(constant -> constant.getLabel().equals(label))
                .findFirst()
                .map(constant -> constant.getValue())
                .orElse(null);
    }

    /**
     * 通过枚举值获取对应的枚举，适用于switch场景
     */
    static <T extends IEnum<I, S>, I, S> T getTypeByValue(Class<T> clazz, I value) {
        T[] constants = clazz.getEnumConstants();
        return Arrays.stream(constants)
                .filter(constant -> Objects.equals(value, constant.getValue()))
                .findFirst()
                .orElse(null);
    }
}
