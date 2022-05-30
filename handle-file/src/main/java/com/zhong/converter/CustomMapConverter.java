package com.zhong.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by cc on 2022/5/29
 */
public class CustomMapConverter implements Converter<Map<String, String>> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return Map.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Map<String, String> convertToJavaData(ReadConverterContext<?> context) throws Exception {
        Map<String, String> map = new HashMap<>();
        Integer rowIndex = context.getReadCellData().getRowIndex();
        String stringValue = context.getReadCellData().getStringValue();
        BigDecimal numberValue = context.getReadCellData().getNumberValue();
        Boolean booleanValue = context.getReadCellData().getBooleanValue();
        if (Objects.nonNull(stringValue)){
            map.put(String.valueOf(rowIndex), stringValue);
        }else if (Objects.nonNull(numberValue)){
            map.put(String.valueOf(rowIndex), String.valueOf(numberValue));
        }else {
            map.put(String.valueOf(rowIndex), String.valueOf(booleanValue));
        }
        return map;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<Map<String, String>> context) throws Exception {
        return new WriteCellData<>(String.valueOf(context.getValue()));
    }
}
