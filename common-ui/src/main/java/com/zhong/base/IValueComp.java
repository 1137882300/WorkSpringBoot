package com.zhong.base;

import com.zhong.CompConstants;

public interface IValueComp extends IComp{

    default String getValue(){
       return (String) get(CompConstants.FIELD_NAME_VALUE);
    }

    default void setValue(String value){
        put(CompConstants.FIELD_NAME_VALUE, value);
    }
}
