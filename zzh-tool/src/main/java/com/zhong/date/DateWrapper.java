package com.zhong.date;

import java.util.Date;

/**
 * @author ashui
 * @date 2021/9/8
 */
public class DateWrapper {
    private Date date;

    public DateWrapper(Date date) {
        this.date = date;
    }

    public void update(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
