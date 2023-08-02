package com.zhong.web.base.validation;

import com.zhong.web.base.validation.annotation.WLListNotEmpty;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author ashui
 * @desc 数据不能为空验证
 * @date 2018/7/3
 */
public class ListNotEmptyValidator implements ConstraintValidator<WLListNotEmpty, List> {


    @Override
    public void initialize(WLListNotEmpty phone) {

    }

    /**
     * @author ashui
     * @desc 数据不能为空验证
     * @date 2018/7/3
     */
    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && list.size() > 0;
    }
}