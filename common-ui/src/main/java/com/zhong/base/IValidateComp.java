package com.zhong.base;

import com.zhong.CompConstants;

import java.util.HashMap;
import java.util.Map;

public interface IValidateComp extends IComp{

    default void innerSetValidate(String regex,String message){
        Map<String,String> validateProps = new HashMap<>();
        validateProps.put(CompConstants.FIELD_NAME_RULE,regex);
        validateProps.put(CompConstants.FIELD_NAME_MESSAGE,message);
        put(CompConstants.FIELD_NAME_VALIDATE,validateProps);
    }


}
