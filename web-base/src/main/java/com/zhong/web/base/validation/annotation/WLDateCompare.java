package com.zhong.web.base.validation.annotation;


import com.zhong.web.base.validation.DateCompareValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日期比较
 *
 * @author ashui
 * @date 2021/8/9
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateCompareValidator.class)
public @interface WLDateCompare {

    /**
     * 开始时间，使用yyyy-MM-dd 格式
     *
     * @author ashui
     * @date 2022/3/1
     */
    String[] startField() default "";

    /**
     * 结束时间，使用yyyy-MM-dd 格式
     *
     * @author ashui
     * @date 2022/3/1
     */
    String[] endField() default "";

    /**
     * 最小天数
     *
     * @author ashui
     * @date 2022/3/1
     */
    int minDay() default 0;

    /**
     * 最大天数
     *
     * @author ashui
     * @date 2022/3/1
     */
    int maxDay() default 0;

    String message() default "日期比较错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
