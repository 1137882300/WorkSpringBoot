package com.zhong.base;

import com.zhong.CompConstants;

public interface ILabelComp extends IComp {

    default String getLabel() {
        return (String) get(CompConstants.FILED_NAME_LABEL);
    }

    default void setLabel(String name) {
        put(CompConstants.FILED_NAME_LABEL, name);
    }

}
