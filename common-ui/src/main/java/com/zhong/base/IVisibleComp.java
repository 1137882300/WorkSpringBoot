package com.zhong.base;

import com.google.common.collect.Maps;
import com.zhong.CompConstants;

import java.util.Map;

public interface IVisibleComp extends IComp{

    default void innerSetVisible(boolean visible){
        put(CompConstants.FIELD_NAME_VISIBLE, visible);
    }

    default void innerSetJsExpression(String expression){
        Map<String, String> value = Maps.newHashMap();
        value.put(CompConstants.FIELD_NAME_TYPE,"JSEXPRESSION");
        value.put(CompConstants.FIELD_NAME_VALUE,expression);
        put(CompConstants.FIELD_NAME_VISIBLE,value);
    }
}
