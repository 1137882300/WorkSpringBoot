package com.zhong.web.base.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * 日期格式验证类基类
 */
public abstract class AbstractDateFormatValidator<A extends Annotation> implements ConstraintValidator<A, String> {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void initialize(A constraintAnnotation) {
        final String format = obtainDateTimeFormat();
        dateTimeFormatter = DateTimeFormatter.ofPattern(format);
    }


    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if (str == null || str.length() == 0) {
            return true;
        }
        try {
            dateTimeFormatter.parse(str);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


    /**
     *
     */
    protected abstract String obtainDateTimeFormat();
}
