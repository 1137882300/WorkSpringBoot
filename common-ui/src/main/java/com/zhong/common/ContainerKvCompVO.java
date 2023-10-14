package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.Map;

public class ContainerKvCompVO extends ElementCompVO implements INameComp, IValueComp, IDisabledComp, IRequiredComp {

    public ContainerKvCompVO(){
        setUiType(CompConstants.UI_TYPE_KV_EDITOR);
    }

    public ContainerKvCompVO innerSetValueMap(Map<String, String> valueMap){
        put(CompConstants.FIELD_NAME_VALUE, valueMap);
        return this;
    }

    public Map<String, String> innerGetValueMap(){
        Object value = get(CompConstants.FIELD_NAME_VALUE);
        if(value instanceof Map){
            return (Map<String, String>) value;
        }
        return null;
    }

}
