package com.zhong.date;


import com.zhong.date.enums.DateFormatEnum;
import com.zhong.date.enums.DateWeekEnum;
import com.zhong.date.enums.ZhDateFormatEnum;
import com.zhong.date.impl.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


/**
 * @author hengyu
 * @date 2021/08/17
 * date工具类
 */
public class WLDate implements IDate, Cloneable {

    private DateWrapper dateWrapper;

    private static WLDate EMPTY = new WLDate();

    private WLDate() {
    }

    private WLDate(Date date) {
        dateWrapper = new DateWrapper(date);
    }

    private WLDate(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        dateWrapper = new DateWrapper(date);
    }

    public static IDate now() {
        return of(new Date());
    }

    public static IDate today() {
        return now().startOfDay();
    }

    public static IDate yesterday() {
        return today().addDays(-1);
    }

    /**
     * @author hengyu
     * @date 2021/9/10
     **/
    public static IDate tomorrow() {
        return today().addDays(1);
    }

    /**
     * date构造IDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    public static IDate of(Date date) {
        return new WLDate(date);
    }
    

    /**
     * 日期对象构造
     *
     * @author ashui
     * @date 2022/4/28
     */
    public static NullableDate ofNullable(Date date) {
        return new NullableDate(date);
    }


    /**
     * 字符串构造: 支持 yyyy-MM-dd HH:mm:ss 和 yyyy-MM-dd 两种格式
     *
     * @author ashui
     * @date 2022/4/28
     */
    public static NullableDate ofNullable(String date) {
        return new NullableDate(date);
    }

    /**
     * 时间戳构造
     *
     * @author ashui
     * @date 2022/4/28
     */
    public static NullableDate ofNullable(Long timestamp) {
        return new NullableDate(timestamp);
    }

    /**
     * localDateTime构造IDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    public static IDate of(LocalDateTime localDateTime) {
        return new WLDate(localDateTime);
    }

    /**
     * 时间戳timestamp构造IDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    public static IDate of(Long timestamp) {
        Date date = new DefaultDateParse().parse(timestamp);
        return new WLDate(date);
    }

    /**
     * 字符串YYYY_MM_dd构造IDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    public static IDate ofDateString(String date) {
        Date dateTemp = new DefaultDateParse().parseDateString(date);
        return new WLDate(dateTemp);
    }

    /**
     * 字符串YYYY_MM_dd_HH_mm_ss构造IDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    public static IDate ofDateTimeString(String datetime) {
        Date dateTemp = new DefaultDateParse().parseDateTimeString(datetime);
        return new WLDate(dateTemp);
    }



    /**
     * 指定格式解析日期
     * @author ashui
     * @date 2022/5/11
     */
    public static IDate of(String datetime, DateFormatEnum dateFormatEnum) {
        Date dateTemp = new DefaultDateParse().parse(datetime,dateFormatEnum);
        return new WLDate(dateTemp);
    }

    /**
     * 年月日时分秒构造IDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    public static IDate of(int year, int month, int day, int hour, int minute, int second) {
        Date date = new DefaultDateParse().parse(year, month, day, hour, minute, second);
        return new WLDate(date);
    }

    /**
     * 年月日构造IDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    public static IDate of(int year, int month, int day) {
        Date date = new DefaultDateParse().parse(year, month, day);
        return new WLDate(date);
    }


    private IDateCompare getDateCompare() {
        return new DefaultDateCompare(dateWrapper);
    }

    private IDateConvert getDateConvert() {
        return new DefaultDateConvert(dateWrapper);
    }

    private IDateFormat getDateFormat() {
        return new DefaultDateFormat(dateWrapper);
    }

    private IDateObtain getDateObtain() {
        return new DefaultDateObtain(dateWrapper);
    }

    private IDateOperation getDateOperation() {
        return new DefaultDateOperation(dateWrapper);
    }

    private IDateDifference getDateDifference() {
        return new DefaultDateDifference(dateWrapper);
    }

    private IDateWeek getDateWeek() {
        return new DefaultDateWeek(dateWrapper);
    }

    /**
     * 比较日期大小
     *
     * @param date
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public boolean before(Date date) {
        return getDateCompare().before(date);
    }

    /**
     * 比较日期大小
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public boolean before(IDate iDate) {
        return getDateCompare().before(iDate);
    }

    /**
     * 比较日期大小
     *
     * @param date
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public boolean after(Date date) {
        return getDateCompare().after(date);
    }

    /**
     * 比较日期大小
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public boolean after(IDate iDate) {
        return getDateCompare().after(iDate);
    }

    /**
     * 是否同一天
     *
     * @param date
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public boolean isSameDay(Date date) {
        return getDateCompare().isSameDay(date);
    }

    /**
     * 是否同一天
     *
     * @param iDate
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public boolean isSameDay(IDate iDate) {
        return getDateCompare().isSameDay(iDate);
    }

    /**
     * 是否同一年
     *
     * @param date
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameYear(Date date) {
        return getDateCompare().isSameYear(date);
    }

    /**
     * 是否同一年
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameYear(IDate iDate) {
        return getDateCompare().isSameYear(iDate);
    }

    /**
     * 同年同月
     *
     * @param date
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameYearAndMonth(Date date) {
        return getDateCompare().isSameYearAndMonth(date);
    }

    /**
     * 同年同月
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameYearAndMonth(IDate iDate) {
        return getDateCompare().isSameYearAndMonth(iDate);
    }

    /**
     * 同月同日
     *
     * @param date
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameMonthAndDay(Date date) {
        return getDateCompare().isSameMonthAndDay(date);
    }

    /**
     * 同月同日
     *
     * @param iDate
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isSameMonthAndDay(IDate iDate) {
        return getDateCompare().isSameMonthAndDay(iDate);
    }

    /**
     * 是否是今年
     *
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isCurrentYear() {
        return getDateCompare().isCurrentYear();
    }

    /**
     * 是否为今天
     *
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isToday() {
        return getDateCompare().isToday();
    }

    /**
     * 是否为昨天
     *
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isYesterday() {
        return getDateCompare().isYesterday();
    }

    /**
     * 是否为明天
     *
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isTomorrow() {
        return getDateCompare().isTomorrow();
    }

    /**
     * 检查是否为00:00:00
     *
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isStartOfDay() {
        return getDateCompare().isStartOfDay();
    }

    /**
     * 检查是是否为23:59:59
     *
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public boolean isEndOfDay() {
        return getDateCompare().isEndOfDay();
    }

    /**
     * toDate
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    @Override
    public Date toDate() {
        return getDateConvert().toDate();
    }

    /**
     * toLocalDateTime
     *
     * @author hengyu
     * @date 2021/9/6
     **/
    @Override
    public LocalDateTime toLocalDateTime() {
        return getDateConvert().toLocalDateTime();
    }

    /**
     * 获取当前时间13位毫秒时间戳
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public Long toTimeStamp() {
        return getDateConvert().toTimeStamp();
    }

    /**
     * 获取当前时间10位秒时间戳
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public Long toTimeStamp10() {
        return getDateConvert().toTimeStamp10();
    }


    /**
     * 时间转字符串:yyyy-MM-dd HH:mm:ss
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public String toDateTimeString() {
        return getDateFormat().toDateTimeString();
    }

    /**
     * 时间转字符串 yyyy-MM-dd
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public String toDateString() {
        return getDateFormat().toDateString();
    }

    /**
     * 时间转字符串:HH:mm:ss
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public String toTimeString() {
        return getDateFormat().toTimeString();
    }

    /**
     * 时间格式化
     *
     * @param formatEnum
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public String format(DateFormatEnum formatEnum) {
        return getDateFormat().format(formatEnum);
    }

    /**
     * 时间中文格式化
     *
     * @param formatZhEnum
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public String format(ZhDateFormatEnum formatZhEnum) {
        return getDateFormat().format(formatZhEnum);
    }

    /**
     * 时间转字符串
     *
     * @param format
     * @author hengyu
     * @date 2021/9/6
     */
    @Override
    public String format(String format) {
        return getDateFormat().format(format);
    }

    /**
     * 当前天0点
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public IDate startOfDay() {
        getDateObtain().startOfDay();
        return this;
    }

    /**
     * 当前天23点59分59
     *
     * @author hengyu
     * @date 2021/8/18
     **/
    @Override
    public IDate endOfDay() {
        getDateObtain().endOfDay();
        return this;
    }

    /**
     * 获取当前月第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate firstDayOfMonth() {
        getDateObtain().firstDayOfMonth();
        return this;
    }

    /**
     * 获取当前月最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate lastDayOfMonth() {
        getDateObtain().lastDayOfMonth();
        return this;
    }

    /**
     * 获取当前年第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate firstDayOfYear() {
        getDateObtain().firstDayOfYear();
        return this;
    }

    /**
     * 获取当前年最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate lastDayOfYear() {
        getDateObtain().lastDayOfYear();
        return this;
    }

    /**
     * 获取当前周第一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate firstDayOfWeek() {
        getDateObtain().firstDayOfWeek();
        return this;
    }

    /**
     * 获取当前周最后一天
     *
     * @author hengyu
     * @date 2021/9/7
     **/
    @Override
    public IDate lastDayOfWeek() {
        getDateObtain().lastDayOfWeek();
        return this;
    }

    /**
     * 返回年份
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer year() {
        return getDateObtain().year();
    }

    /**
     * 返回月
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer month() {
        return getDateObtain().month();
    }

    /**
     * 返回日
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer day() {
        return getDateObtain().day();
    }

    /**
     * 返回小时
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer hour() {
        return getDateObtain().hour();
    }

    /**
     * 返回分钟
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer minute() {
        return getDateObtain().minute();
    }

    /**
     * 返回秒
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public Integer second() {
        return getDateObtain().second();
    }

    /**
     * 日期增减年
     *
     * @param num
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public IDate addYears(int num) {
        getDateOperation().addYears(num);
        return this;
    }

    /**
     * 日期增减月份
     *
     * @param num
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public IDate addMonths(int num) {
        getDateOperation().addMonths(num);
        return this;
    }

    /**
     * 日期增减周
     *
     * @param num
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public IDate addWeeks(int num) {
        getDateOperation().addWeeks(num);
        return this;
    }

    /**
     * 日期增减周
     *
     * @param num
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public IDate addDays(int num) {
        getDateOperation().addDays(num);
        return this;
    }

    /**
     * 日期增减小时
     *
     * @param num
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public IDate addHours(int num) {
        getDateOperation().addHours(num);
        return this;
    }

    /**
     * 日期增减分
     *
     * @param num
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public IDate addMinutes(int num) {
        getDateOperation().addMinutes(num);
        return this;
    }

    /**
     * 日期增减秒
     *
     * @param num
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public IDate addSeconds(int num) {
        getDateOperation().addSeconds(num);
        return this;
    }

    /**
     * 日期增减毫秒
     *
     * @param num
     * @author hengyu
     * @date 2021/8/19
     */
    @Override
    public IDate addMilliseconds(int num) {
        getDateOperation().addMilliseconds(num);
        return this;
    }

    /**
     * 计算两个日期相差年数
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInYears(Date endDate) {
        return getDateDifference().diffInYears(endDate);
    }

    /**
     * 计算两个日期相差年数
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInYears(IDate endIDate) {
        return diffInYears(endIDate.toDate());
    }

    /**
     * 计算两个日期相差月数
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInMonths(Date endDate) {
        return getDateDifference().diffInMonths(endDate);
    }

    /**
     * 计算两个日期相差月数
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInMonths(IDate endIDate) {
        return diffInMonths(endIDate.toDate());
    }

    /**
     * 计算两个日期相差天数
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInDays(Date endDate) {
        return getDateDifference().diffInDays(endDate);
    }

    /**
     * 计算两个日期相差天数
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInDays(IDate endIDate) {
        return diffInDays(endIDate.toDate());
    }

    /**
     * 计算两个日期相差时
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInHours(Date endDate) {
        return getDateDifference().diffInHours(endDate);
    }

    /**
     * 计算两个日期相差时
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInHours(IDate endIDate) {
        return diffInHours(endIDate.toDate());
    }

    /**
     * 计算两个日期相差分
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInMinutes(Date endDate) {
        return getDateDifference().diffInMinutes(endDate);
    }

    /**
     * 计算两个日期相差分
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInMinutes(IDate endIDate) {
        return diffInMinutes(endIDate.toDate());
    }

    /**
     * 计算两个日期相差秒
     *
     * @param endDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInSeconds(Date endDate) {
        return getDateDifference().diffInSeconds(endDate);
    }

    /**
     * 计算两个日期相差秒
     *
     * @param endIDate
     * @author hengyu
     * @date 2021/8/18
     */
    @Override
    public int diffInSeconds(IDate endIDate) {
        return diffInSeconds(endIDate.toDate());
    }

    /**
     * 返回周几
     *
     * @author hengyu
     * @date 2021/9/9
     **/
    @Override
    public String weekDay() {
        return getDateWeek().weekDay();
    }

    /**
     * 根据枚举返回周几格式
     * see {@link DateWeekEnum}
     *
     * @param dateWeekEnum
     * @author hengyu
     * @date 2021/9/9
     */
    @Override
    public String weekDay(DateWeekEnum dateWeekEnum) {
        return getDateWeek().weekDay(dateWeekEnum);
    }

    /**
     * @author: 竹阳
     * @date: 2022/11/17
     * 返回星期几数字
     */
    @Override
    public Integer weekDayNum() {
        return getDateWeek().weekDayNum();
    }

    /**
     * 克隆当前对象
     *
     * @author hengyu
     * @date 2021/9/10
     **/
    @Override
    public IDate clone() {
        IDate clone = null;
        try {
            clone = (IDate) super.clone();
        } catch (CloneNotSupportedException e) {

        }
        return clone;
    }
}
