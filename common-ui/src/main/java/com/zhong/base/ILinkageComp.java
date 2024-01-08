package com.zhong.base;

import com.zhong.CompConstants;

import java.util.HashMap;
import java.util.Map;

public interface ILinkageComp extends IComp{

    default void innerLinkageExpression(Map<String, String> map){
        put(CompConstants.FIELD_NAME_LINKAGE, map);
    }


    /**
     * 依赖某一字段必填
     * @param value
     */
    default void innerLinkageExpression(String value){
        Map<String,String> props = new HashMap<>();
        props.put(CompConstants.FIELD_NAME_TYPE,"JSEXPRESSION");
        props.put(CompConstants.FIELD_NAME_VALUE,value);
        put(CompConstants.FIELD_NAME_LINKAGE,props);
    }

    default void innerLinkageExpression(String key, String value){
        Map<String,String> props = new HashMap<>();
        props.put(CompConstants.FIELD_NAME_KEY,key);
        props.put(CompConstants.FIELD_NAME_VALUE,value);
        put(CompConstants.FIELD_NAME_LINKAGE,props);
    }
}
