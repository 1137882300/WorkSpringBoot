package com.zhong.web.base.validation.annotation;


import com.zhong.web.base.validation.FieldValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author haitao
 * @desc 用于范围类字段的校验
 * @date 2021/05/14
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FieldValueValidator.class})
public @interface FieldValue {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] values() default "";

    Class<? extends Enum> enumClass() default Enum.class;
}

