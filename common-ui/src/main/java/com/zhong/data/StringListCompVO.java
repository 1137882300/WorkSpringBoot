package com.zhong.data;

import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.List;

public class StringListCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequiredComp, IDisabledComp, IPlaceholderComp, IVisibleComp, IValidateComp, IBatchUpdateComp {

    public String getValue(){
        return (String) get(CompConstants.FIELD_NAME_VALUE);
    }

    public StringListCompVO setValue(String value){
        put(CompConstants.FIELD_NAME_VALUE,value);
        return this;
    }

    public StringListCompVO setFixValue(List<String> fixValues){
        put(CompConstants.FIELD_NAME_FIX_VALUE, fixValues);
        return this;
    }

    public List<String> getValues(){
        return (List<String>) get(CompConstants.FIELD_NAME_VALUE);
    }

    public StringListCompVO setValues(List<String> values){
        put(CompConstants.FIELD_NAME_VALUE, values);
        return this;
    }

    public StringListCompVO(){
        setUiType(CompConstants.UI_TYPE_STRING_LIST);
    }

    public void innerSetMaxLength(int length){
        put(CompConstants.FIELD_NAME_MAX_LENGTH, length);
    }

}
