package com.zhong.web.base.validation;


import com.zhong.web.base.validation.annotation.WLPhone;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ashui
 * @desc 手机号码验证类
 * @date 2018/7/3
 */
public class PhoneValidator implements ConstraintValidator<WLPhone, String> {

    private boolean hasValueValid;

    public void setHasValueValid(boolean hasValueValid) {
        this.hasValueValid = hasValueValid;
    }

    @Override
    public void initialize(WLPhone phone) {
        hasValueValid = phone.hasValueValid();
    }


    /**
     * @author ashui
     * @desc 数据不能为空验证
     * @date 2018/7/3
     */
    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if (str != null && str.length() > 0) {
            return checkPhone(str);
        }
        return hasValueValid;
    }

    public boolean checkPhone(String phone) {
        if (phone == null || phone.length() == 0) {
            return false;
        }
        String rex = "^1[3456789]{1}\\d{9}$";
        return phone.matches(rex);
    }

}