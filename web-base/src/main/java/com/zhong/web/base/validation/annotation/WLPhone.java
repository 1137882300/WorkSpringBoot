package com.zhong.web.base.validation.annotation;


import com.zhong.web.base.validation.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 电话格式是否正确
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
public @interface WLPhone {
    String message() default "电话格式错误";

    //是否有值才验证，默认false 则输入空返回失败
    boolean hasValueValid() default false;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}