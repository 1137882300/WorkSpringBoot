package com.zhong.data;

import com.zhong.CompConstants;

import java.util.HashMap;
import java.util.Map;

public class SelectOptionVO extends HashMap<String,Object> {

    public SelectOptionVO setLabel(String label){
        put(CompConstants.FILED_NAME_LABEL,label);
        return this;
    }

    public String getLabel(){
        return (String) get(CompConstants.FILED_NAME_LABEL);
    }

    public SelectOptionVO setValue(String value){
        put(CompConstants.FIELD_NAME_VALUE,value);
        return this;
    }

    public SelectOptionVO setBooleanValue(Boolean value){
        put(CompConstants.FIELD_NAME_VALUE,value);
        return this;
    }

    public String getValue(){
        return (String) get(CompConstants.FIELD_NAME_VALUE);
    }

    public void innerSetExtraValue(String extraValue) {
        put(CompConstants.FIELD_NAME_EXTRA_VALUE,extraValue);
    }

    public String innerGetExtraValue() {
        return (String) get(CompConstants.FIELD_NAME_EXTRA_VALUE);
    }

    public SelectOptionVO setI18n(Map<String, String> i18n){
        put(CompConstants.FIELD_NAME_I18N, i18n);
        return this;
    }

    public SelectOptionVO disable(Boolean disable){
        if(Boolean.TRUE.equals(disable)){
            put(CompConstants.FIELD_NAME_DISABLE,Boolean.TRUE.toString());
        }
        return this;
    }

    public void innerSetImg(String img){
        put(CompConstants.FIELD_NAME_IMG, img);
    }

    public void setUnDeletable(Boolean value){
        put(CompConstants.FIELD_NAME_UN_DELETE_ABLE,value);
    }
}
