package com.zhong.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.zhong.converter.CustomListConverter;
import com.zhong.converter.CustomMapConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cc on 2022/5/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ExcelTestEntity {

    @ExcelProperty(value = {"主标题", "Integer类型"}, index = 2)
    private String realName;

    @ExcelProperty(value = {"主标题", "Integer类型"}, index = 3)
    private String phone;

    @ExcelProperty(value = {"主标题", "Integer类型"}, index = 12)
    private String openId;

    @ExcelProperty(value = {"主标题", "Integer类型"}, index = 13)
    private Integer status;
}

