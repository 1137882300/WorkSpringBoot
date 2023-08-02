package com.zhong.date;

import com.zhong.date.enums.DateFormatEnum;
import com.zhong.date.enums.ZhDateFormatEnum;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author ashui
 * @date 2022/4/28
 */
public class NullableDate {
    private Optional<Supplier<IDate>> dateSupplier;

    private NullableDate(boolean isNull, Supplier<IDate> dateSupplier) {
        this.dateSupplier = isNull ? Optional.empty() : Optional.ofNullable(dateSupplier);
    }

    public NullableDate(Date date) {
        this(date == null, () -> WLDate.of(date));
    }


    public NullableDate(String date) {
        if (StringUtils.isNotBlank(date)) {
            if (date.length() == 10) {
                dateSupplier = Optional.of(() -> WLDate.ofDateString(date));
            } else {
                dateSupplier = Optional.of(() -> WLDate.ofDateTimeString(date));
            }
        } else {
            this.dateSupplier = Optional.empty();
        }

    }

    public NullableDate(Long timestamp) {
        this(ObjectUtils.isEmpty(timestamp), () -> WLDate.of(timestamp));
    }

    /**
     * 时间转字符串:yyyy-MM-dd HH:mm:ss
     *
     * @author ashui
     * @date 2022/4/28
     */
    public Optional<String> toDateTimeString() {
        return dateSupplier.map(it -> Optional.ofNullable(it.get().toDateTimeString())).orElse(Optional.empty());
    }

    /**
     * 时间转字符串:yyyy-MM-dd
     *
     * @author ashui
     * @date 2022/4/28
     */
    public Optional<String> toDateString() {
        return dateSupplier.map(it -> Optional.ofNullable(it.get().toDateString())).orElse(Optional.empty());
    }


    /**
     * 操作日期为当前天23点59分59
     *
     * @author ashui
     * @date 2022/4/28
     */
    public NullableDate endOfDay() {
        if(dateSupplier.isPresent()){
            final IDate iDate = dateSupplier.get().get().endOfDay();
            this.dateSupplier = Optional.of(() -> iDate);
        }
        return this;
    }



    /**
     * 时间转字符串:HH:mm:ss
     *
     * @author ashui
     * @date 2022/4/28
     **/
    public Optional<String> toTimeString() {
        return dateSupplier.map(it -> it.get().toTimeString());
    }


    /**
     * 时间格式化
     *
     * @author ashui
     * @date 2022/4/28
     **/
    public Optional<String> format(DateFormatEnum formatEnum) {
        return dateSupplier.map(it -> it.get().format(formatEnum));
    }


    /**
     * 时间中文格式化
     *
     * @author ashui
     * @date 2022/4/28
     **/
    public Optional<String> format(ZhDateFormatEnum formatZhEnum) {
        return dateSupplier.map(it -> it.get().format(formatZhEnum));
    }


    /**
     * 时间转字符串
     *
     * @author ashui
     * @date 2021/9/6
     **/
    public Optional<String> format(String format) {
        return dateSupplier.map(it -> Optional.ofNullable(it.get().format(format))).orElse(Optional.empty());
    }


    /**
     * toDate
     *
     * @author ashui
     * @date 2021/9/6
     **/
    public Optional<Date> toDate() {
        return dateSupplier.map(it -> Optional.ofNullable(it.get().toDate())).orElse(Optional.empty());
    }

    /**
     * toLocalDateTime
     *
     * @author ashui
     * @date 2021/9/6
     **/
    public Optional<LocalDateTime> toLocalDateTime() {
        return dateSupplier.map(it -> Optional.ofNullable(it.get().toLocalDateTime())).orElse(Optional.empty());
    }

    /**
     * 获取当前时间13位毫秒时间戳
     *
     * @author ashui
     * @date 2021/9/7
     **/
    public Optional<Long> toTimeStamp() {
        return dateSupplier.map(it -> Optional.ofNullable(it.get().toTimeStamp())).orElse(Optional.empty());
    }

    /**
     * 获取当前时间10位秒时间戳
     *
     * @author ashui
     * @date 2021/9/7
     **/
    public Optional<Long> toTimeStamp10() {
        return dateSupplier.map(it -> Optional.ofNullable(it.get().toTimeStamp10())).orElse(Optional.empty());
    }


}
