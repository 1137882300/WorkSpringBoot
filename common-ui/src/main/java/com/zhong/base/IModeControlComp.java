package com.zhong.base;

import com.zhong.CompConstants;

/**
 * @author: zhuang wei
 * @date: 2022/8/31
 **/
public interface IModeControlComp extends IComp {

    default void setModeMultiple() {
        put(CompConstants.FIELD_NAME_MODE, "multiple");
    }

    default void setControlMain() {
        put(CompConstants.FIELD_NAME_CONTROL, "main");
    }

}
