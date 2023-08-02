package com.zhong.web.base.validation;


import com.zhong.web.base.validation.annotation.WLDateFormatHHmmss;

/**
 * HH:mm:ss日期格式验证类
 *
 * @author ashui
 * @date 2021/8/9
 */
public class DateFormatHHmmssValidator extends AbstractDateFormatValidator<WLDateFormatHHmmss> {
    
    /**
     * @author ashui
     * @date 2021/8/9
     */
    @Override
    protected String obtainDateTimeFormat() {
        return "HH:mm:ss";
    }

}

