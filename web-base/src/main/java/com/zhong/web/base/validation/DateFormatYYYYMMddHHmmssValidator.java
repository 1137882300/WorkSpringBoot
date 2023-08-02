package com.zhong.web.base.validation;


import com.zhong.web.base.validation.annotation.WLDateFormatYYYYMMddHHmmss;

/**
 * yyyy-MM-dd HH:mm:ss日期格式验证类
 *
 * @author ashui
 * @date 2021/8/9
 */
public class DateFormatYYYYMMddHHmmssValidator extends AbstractDateFormatValidator<WLDateFormatYYYYMMddHHmmss> {

    /**
     * @author ashui
     * @date 2021/8/9
     */
    @Override
    protected String obtainDateTimeFormat() {
        return "yyyy-MM-dd HH:mm:ss";
    }


}
