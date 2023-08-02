package com.zhong.web.base.validation;


import com.zhong.web.base.validation.annotation.WLDateFormatYYYYMM;

/**
 * yyyy-MM 日期格式验证类
 *
 * @author ashui
 * @date 2021/8/9
 */
public class DateFormatYYYYMMValidator extends AbstractDateFormatValidator<WLDateFormatYYYYMM> {

    /**
     * @author ashui
     * @date 2021/8/9
     */
    @Override
    protected String obtainDateTimeFormat() {
        return "yyyy-MM";
    }

}

