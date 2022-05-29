package com.zhong.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by cc on 2022/5/29
 */
public class CustomListConverter implements Converter<List<String>> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return List.class;
    }

    /**
     * Excel 的数据类型
     *
     * @return
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public List<String> convertToJavaData(ReadConverterContext<?> context) throws Exception {
        String stringValue = context.getReadCellData().getStringValue();
        BigDecimal numberValue = context.getReadCellData().getNumberValue();
        Boolean booleanValue = context.getReadCellData().getBooleanValue();
        if (Objects.nonNull(stringValue)){
            return Collections.singletonList(stringValue);
        }else if (Objects.nonNull(numberValue)){
            return Collections.singletonList(String.valueOf(numberValue));
        }else {
            return Collections.singletonList(String.valueOf(booleanValue));
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<List<String>> context) throws Exception {
        return null;
    }
}
