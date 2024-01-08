package com.zhong.base;

import com.google.common.collect.Maps;
import com.zhong.CompConstants;

import java.util.Map;

/**
 * @author zhangwenju
 * @create 2022-06-0921:20
 */
public interface IDisplayComp extends IComp {

    default void innerSetDisplay(boolean display) {
        put(CompConstants.FIELD_NAME_DISPLAY, display);
    }

    default void innerSetDisplayJsExpression(String expression) {
        Map<String, String> value = Maps.newHashMap();
        value.put(CompConstants.FIELD_NAME_TYPE, "JSEXPRESSION");
        value.put(CompConstants.FIELD_NAME_VALUE, expression);
        put(CompConstants.FIELD_NAME_DISPLAY, value);
    }
}
