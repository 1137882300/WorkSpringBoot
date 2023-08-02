package com.zhong.vo;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * office 条件列表
 */
@Data
public class OfficeOptionItemVO<T> {

    private T value;
    private String label;

    public OfficeOptionItemVO(T value, String label) {
        this.value = value;
        this.label = label;
    }

    public OfficeOptionItemVO() {
    }


    public static <T> OfficeOptionItemVO<T> of(T value, String label) {
        return new OfficeOptionItemVO<>(value, label);
    }


    /**
     * 通过枚举转换列表
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<?>, T> List<OfficeOptionItemVO<T>> createByEnum(Class<E> clazz) {
        E[] enumConstants = clazz.getEnumConstants();
        Method valueMethod;
        Method labelMethod;
        try {
            valueMethod = clazz.getMethod("getValue");
            labelMethod = clazz.getMethod("getLabel");
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        return Arrays.stream(enumConstants)
                .map(it -> {
                    try {
                        T value = (T) valueMethod.invoke(it);
                        String label = (String) labelMethod.invoke(it);
                        return new OfficeOptionItemVO<>(value, label);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                })
                .collect(Collectors.toList());
    }


    /**
     * 通过list转换列表
     */
    public static <T, E> List<OfficeOptionItemVO<T>> createByList(List<E> list, Function<E, T> valueSup, Function<E, String> labelSup) {
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }
        return list.stream()
                .map(it -> new OfficeOptionItemVO<>(valueSup.apply(it), labelSup.apply(it)))
                .collect(Collectors.toList());
    }

    /**
     * 通过map转换列表
     */
    public static <T, E> List<OfficeOptionItemVO<T>> createByMap(HashMap<T, String> hashMap) {
        if (hashMap == null || hashMap.size() == 0) {
            return new ArrayList<>();
        }
        return hashMap.entrySet().stream()
                .map(entry -> new OfficeOptionItemVO<>(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }


}