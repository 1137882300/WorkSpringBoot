package com.zhong.web.base.validation;


import com.zhong.web.base.validation.annotation.WLNotNullOrZero;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ashui
 * @desc 数值是否为null或者0
 * @date 2018/7/3
 */
public class NotNullOrZeroValidator implements ConstraintValidator<WLNotNullOrZero, Number> {


    @Override
    public void initialize(WLNotNullOrZero phone) {

    }

    /**
     * @author ashui
     * @desc 数值是否为null或者0
     * @date 2018/7/3
     */
    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        if (number == null) {
            return false;
        }

        if (number.intValue() == 0) {
            return false;
        }
        return true;
    }
}