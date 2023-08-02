package com.zhong.web.base.validation;


import com.zhong.web.base.validation.annotation.WLEnumContain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * 枚举状态值范围校验
 *
 * @author 卡尔
 * @date 2022/02/18 17:00
 */
public class EnumContainValidator implements ConstraintValidator<WLEnumContain, Object> {

    private String[] values;

    @Override
    public void initialize(WLEnumContain contain) {
        this.values = contain.values();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null  || String.valueOf(value).trim().length() <= 0) {
            // 当状态为空时使用默认值
            return true;
        }
        Optional<String> first = Stream.of(values).filter(x -> x.equals(String.valueOf(value))).findFirst();
        return first.isPresent();
    }
}
