package com.zhong.web.base.validation.annotation;


import com.zhong.web.base.validation.ListNotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数组是否为null或者长度为0
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListNotEmptyValidator.class )
public @interface WLListNotEmpty {
    String message() default "不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}