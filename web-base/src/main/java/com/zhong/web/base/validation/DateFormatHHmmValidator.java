package com.zhong.web.base.validation;


import com.zhong.web.base.validation.annotation.WLDateFormatHHmm;

/**
 * HH:mm:ss日期格式验证类
 *
 * @author ashui
 * @date 2021/8/9
 */
public class DateFormatHHmmValidator extends AbstractDateFormatValidator<WLDateFormatHHmm> {
    
    /**
     * @author ashui
     * @date 2021/8/9
     */
    @Override
    protected String obtainDateTimeFormat() {
        return "HH:mm";
    }

}

