package com.zhong.web.base.validation.annotation;


import com.zhong.web.base.validation.DateFormatHHmmValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HH:mm日期格式验证
 *
 * @author ashui
 * @date 2021/8/9
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatHHmmValidator.class)
public @interface WLDateFormatHHmm {

    String message() default "日期格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}