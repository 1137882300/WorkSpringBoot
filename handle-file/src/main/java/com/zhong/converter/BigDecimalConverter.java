package com.zhong.converter;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.ReadCellData;

import java.math.BigDecimal;

/**
 * @author: juzi
 * @date: 2023/7/31
 * @desc:
 */
public class BigDecimalConverter implements Converter<BigDecimal> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return BigDecimal.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.NUMBER;
    }

    @Override
    public BigDecimal convertToJavaData(ReadConverterContext<?> context) {
        AnalysisContext analysisContext = context.getAnalysisContext();
        ReadCellData<?> readCellData = context.getReadCellData();
        return readCellData.getNumberValue();
    }
}
