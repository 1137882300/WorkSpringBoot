package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.List;
import java.util.Map;

public class InputLanguageCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequiredComp, IDisabledComp, IPlaceholderComp, IVisibleComp, IValidateComp, ILanguageComp {

    public Map<String, String> getLanguageValue(){
        return (Map<String, String>) get(CompConstants.FIELD_NAME_VALUE);
    }


    public InputLanguageCompVO setLanguageValue(Map<String, String> value){
        put(CompConstants.FIELD_NAME_VALUE,value);
        return this;
    }

    public InputLanguageCompVO setLanguageValues(List<Map<String, String>> values){
        put(CompConstants.FIELD_NAME_VALUE,values);
        return this;
    }

    public InputLanguageCompVO innerSetFixValue(List<Map<String, String>> fixValues){
        put(CompConstants.FIELD_NAME_FIX_VALUE, fixValues);
        return this;
    }

    public InputLanguageCompVO innerSetUnDeletedValue(List<Map<String, String>> unDeletedValues){
        put(CompConstants.FIELD_NAME_UN_DELETED_VALUE, unDeletedValues);
        return this;
    }


    public List<Map<String, String>> getLanguageValues(){
        return (List<Map<String, String>>) get(CompConstants.FIELD_NAME_VALUE);
    }

    public InputLanguageCompVO(){
        setUiType(CompConstants.UI_TYPE_INPUT_LANGUAGE);
    }

    public void innerSetMaxLength(int length){
        put(CompConstants.FIELD_NAME_MAX_LENGTH, length);
    }

    public InputLanguageCompVO innerSetWidth(int width){
        put(CompConstants.FIELD_NAME_WIDTH,width);
        return this;
    }
}
