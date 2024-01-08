package com.zhong.base;

import com.zhong.CompConstants;

public interface INameComp extends IComp {

    default String getName() {
        return (String) get(CompConstants.FIELD_NAME_NAME);
    }

    default void setName(String name) {
        put(CompConstants.FIELD_NAME_NAME, name);
    }
}
