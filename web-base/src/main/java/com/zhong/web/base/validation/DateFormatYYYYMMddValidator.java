package com.zhong.web.base.validation;


import com.zhong.web.base.validation.annotation.WLDateFormatYYYYMMdd;

/**
 * yyyy-MM-dd 日期格式验证类
 *
 * @author ashui
 * @date 2021/8/9
 */
public class DateFormatYYYYMMddValidator extends AbstractDateFormatValidator<WLDateFormatYYYYMMdd> {

    /**
     * @author ashui
     * @date 2021/8/9
     */
    @Override
    protected String obtainDateTimeFormat() {
        return "yyyy-MM-dd";
    }

}

