package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.List;

public class InputCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequiredComp, IDisabledComp,IDisabledContentComp,IDisabledCustomizeComp, IPlaceholderComp, IVisibleComp, IValidateComp, IBatchUpdateComp, IToolTipComp {

    public String getValue(){
        return (String) get(CompConstants.FIELD_NAME_VALUE);
    }

    public InputCompVO setValue(String value){
        put(CompConstants.FIELD_NAME_VALUE,value);
        return this;
    }

    public InputCompVO setFixValue(List<String> fixValues){
        put(CompConstants.FIELD_NAME_FIX_VALUE, fixValues);
        return this;
    }

    public List<String> getValues(){
        return (List<String>) get(CompConstants.FIELD_NAME_VALUE);
    }

    public InputCompVO setValues(List<String> values){
        put(CompConstants.FIELD_NAME_VALUE, values);
        return this;
    }

    public InputCompVO(){
        setUiType(CompConstants.UI_TYPE_INPUT);
    }

    public void innerSetMaxLength(int length){
        put(CompConstants.FIELD_NAME_MAX_LENGTH, length);
    }

    public void innerSetToolTip(String tooTip){
        put(CompConstants.FIELD_NAME_TOOLTIPS, tooTip);
    }

    public void innerSetMax(int max){
        put(CompConstants.FIELD_NAME_MAX, max);
    }
}
