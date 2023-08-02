package com.zhong.web.base.validation;

import com.zhong.date.WLDate;
import com.zhong.web.base.validation.annotation.WLDateCompare;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * 日期比较
 *
 */
@Slf4j
public class DateCompareValidator implements ConstraintValidator<WLDateCompare, Object> {
    
    private String[] startField;
    private String[] endField;
    private int minDay;
    private int maxDay;

    @Override
    public void initialize(WLDateCompare dateCompare) {
        startField = dateCompare.startField();
        endField = dateCompare.endField();
        minDay = dateCompare.minDay();
        maxDay = dateCompare.maxDay();
    }


    /**
     * 比较两个日期
     *
     * @author ashui
     * @date 2022/3/1
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (startField.length == 0 || endField.length == 0) {
                return true;
            }
            if (startField.length != endField.length) {
                return true;
            }
            for (int i = 0; i < startField.length; i++) {
                boolean flag = doValid(startField[i], endField[i], object, constraintValidatorContext);
                if (!flag) {
                    return flag;
                }
            }

        } catch (Exception ex) {
            log.error("isValid", ex);
            return false;
        }
        return true;
    }

    private boolean doValid(String start, String end, Object object, ConstraintValidatorContext constraintValidatorContext) {
        Object valStart = getVal(object, start);
        Object valEnd = getVal(object, end);

        if (valStart == null || valEnd == null) {
            return true;
        }

        Date dataStart = WLDate.ofDateString(valStart.toString()).toDate();
        Date dataEnd = WLDate.ofDateString(valEnd.toString()).toDate();

        if (minDay > 0 || maxDay > 0) {
            int days = WLDate.of(dataStart).diffInDays(dataEnd);
            if (minDay > 0 && maxDay > 0) {
                return days > minDay && days < maxDay;
            } else if (minDay > 0 && maxDay == 0) {
                return days > minDay;
            } else if (minDay == 0 && maxDay > 0) {
                return days < maxDay;
            }
        }
        return WLDate.of(dataStart).before(dataEnd);
    }


    private Object getVal(Object object, String date) {
        Field field = ReflectionUtils.findField(object.getClass(), date);
        field.setAccessible(true);
        return ReflectionUtils.getField(field, object);
    }


}