package com.zhong.web.base.validation;

import com.zhong.web.base.validation.annotation.FieldValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class FieldValueValidator implements ConstraintValidator<FieldValue, Object> {

    private String[] values;

    @Override
    public void initialize(FieldValue arg0) {
        this.values = arg0.values();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        AtomicBoolean atomicValid = new AtomicBoolean(false);
        if (value == null) {
            //当状态为空时使用默认值
            return true;
        }
        Stream.of(values).filter(x -> x.equals(String.valueOf(value))).findFirst().ifPresent(s -> atomicValid.set(true));
        return atomicValid.get();
    }
}
