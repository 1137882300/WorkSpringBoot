package com.zhong.web.base.validation.annotation;


import com.zhong.web.base.validation.EnumContainValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 枚举状态值范围校验
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumContainValidator.class})
public @interface WLEnumContain {

    String message() default "输入值不在枚举值范围中！";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] values() default "";
}

