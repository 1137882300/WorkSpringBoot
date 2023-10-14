package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.Objects;

/**
 * @author: zhuang wei
 * @date: 2022/1/26
 **/
public class RadioCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequiredComp, IDisabledComp, IOptionsComp{
    public RadioCompVO(){
        setUiType(CompConstants.UI_TYPE_RADIO);
    }

    public RadioCompVO setValue(String value){
        put(CompConstants.FIELD_NAME_VALUE,value);
        return this;
    }

    public RadioCompVO setRowsRadio(boolean rowsRadio){
        put(CompConstants.FIELD_NAME_ROWS_RADIO, rowsRadio);
        return this;
    }

    public String getValue(){
        Object value = get(CompConstants.FIELD_NAME_VALUE);
        return Objects.nonNull(value)?value.toString():null;
    }
}
