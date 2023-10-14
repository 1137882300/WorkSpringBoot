package com.zhong.base;

import com.zhong.CompConstants;

import java.util.HashMap;
import java.util.Map;

public interface IDisabledComp extends IComp{

    default void innerDisable(){
        put(CompConstants.FIELD_NAME_DISABLE,true);
    }

    default void innerDisableExpression(String value){
        Map<String,String> props = new HashMap<>();
        props.put(CompConstants.FIELD_NAME_TYPE,"JSEXPRESSION");
        props.put(CompConstants.FIELD_NAME_VALUE,value);
        put(CompConstants.FIELD_NAME_DISABLE,props);
    }

}
