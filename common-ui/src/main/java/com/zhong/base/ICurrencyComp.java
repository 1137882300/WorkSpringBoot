package com.zhong.base;

import com.google.common.collect.Maps;
import com.zhong.CompConstants;

import java.util.Map;

/**
 * @author zhangwenju
 * @create 2022-06-0921:22
 */
public interface ICurrencyComp extends IComp {

    default void innerSetCurrency(boolean currency){
        put(CompConstants.FIELD_NAME_CURRENCY, currency);
    }

    default void innerSetCurrencyJsExpression(String expression){
        Map<String, String> value = Maps.newHashMap();
        value.put(CompConstants.FIELD_NAME_TYPE,"JSEXPRESSION");
        value.put(CompConstants.FIELD_NAME_VALUE,expression);
        put(CompConstants.FIELD_NAME_CURRENCY,value);
    }
}
