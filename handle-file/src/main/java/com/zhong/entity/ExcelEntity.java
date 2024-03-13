package com.zhong.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by cc on 2022/5/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExcelEntity {

    @ExcelProperty(value = {"主标题", "Integer类型"}, index = 0)
    private Integer integerData;

    @ExcelProperty(value = {"主标题", "String类型"}, index = 1)
    private String stringData;

    @ExcelProperty(value = {"主标题", "Long类型"}, index = 2)
    private Long longData;

    @ExcelProperty(value = {"主标题", "Date类型"}, index = 3)
    private Date dateData;

    @ExcelProperty(value = {"主标题", "Double类型"}, index = 4)
    @NumberFormat("#.##%")
    private Double doubleData;

//    @ExcelProperty(value = {"主标题", "BigDecimal类型"}, index = 3, converter = BigDecimalConverter.class)
//    private BigDecimal money;

//    @ExcelProperty(value = {"主标题", "Map类型"}, index = 5, converter = CustomMapConverter.class)
//    private Map<String, String> mapData;
//
//    @ExcelProperty(value = {"主标题", "Boolean类型"}, index = 6)
//    private Boolean booleanData;
//
//    @ExcelProperty(value = {"主标题", "List类型"}, index = 7, converter = CustomListConverter.class)
//    private List<String> listData;

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}
