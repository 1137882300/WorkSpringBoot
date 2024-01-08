package com.zhong.base;

import com.zhong.CompConstants;

import java.util.HashMap;
import java.util.Map;

public interface IRequiredComp extends IComp{

    default void innerSetRequired(boolean required){
        put(CompConstants.FIELD_NAME_REQUIRED,required);
    }

    default void innerSetExpression(Map<String, String> map){
        put(CompConstants.FIELD_NAME_REQUIRED, map);
    }

    /**
     * 依赖某一字段必填
     * @param value
     */
    default void innerRequiredRelatedExpression(String value){
        Map<String,String> props = new HashMap<>();
        props.put(CompConstants.FIELD_NAME_TYPE,"JSEXPRESSION");
        props.put(CompConstants.FIELD_NAME_VALUE,value);
        put(CompConstants.FIELD_NAME_REQUIRED,props);
    }
}
