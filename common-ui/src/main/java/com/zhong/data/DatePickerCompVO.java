package com.zhong.data;

import com.zhong.CompConstants;
import com.zhong.base.BaseCompVO;
import com.zhong.base.IDisabledComp;
import com.zhong.base.INameComp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class DatePickerCompVO extends BaseCompVO implements INameComp, IDisabledComp {

    /**
     * 默认东八区
     * */
    private final static TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone("GMT+8");

    private final static String DEFAULT_DATE_FORMAT = "YYYY-MM-dd'T'hh:mm:ss.SSS'Z'";


    public DatePickerCompVO(){
        setUiType(CompConstants.UI_TYPE_DATE_PICKER);
    }

    public Long innerGetTimeMillis(){
        String dateStr = (String) get(CompConstants.FIELD_NAME_VALUE);
        if(StringUtils.isNotEmpty(dateStr)){
            try {
                SimpleDateFormat dateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);
                dateFormat.setTimeZone(DEFAULT_TIME_ZONE);
                Instant instant = Instant.parse(dateStr);
                //Date date=dateFormat.parse(dateStr);
                //return date.toInstant().toEpochMilli();
                return instant.toEpochMilli();
            }catch (Throwable e){
                log.error("DatePickerCompVO#parse error,value:{}",dateStr);
            }
        }
        return null;
    }

    public void innerSetTimeMillis(Long timeMillis){
        if(timeMillis!=null){
            SimpleDateFormat dateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            dateFormat.setTimeZone(DEFAULT_TIME_ZONE);
            Date date = Date.from(Instant.ofEpochMilli(timeMillis));
            put(CompConstants.FIELD_NAME_VALUE,dateFormat.format(date));
        }
    }
}
