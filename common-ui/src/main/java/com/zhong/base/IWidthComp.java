package com.zhong.base;

import com.zhong.CompConstants;

import java.util.Optional;

public interface IWidthComp extends IComp {

    default String getWidth() {
        return Optional.ofNullable(get(CompConstants.FIELD_NAME_WIDTH)).map(String::valueOf).orElse("0");
    }

    default void setWidth(Float width) {
        put(CompConstants.FIELD_NAME_WIDTH, Optional.ofNullable(width).map(String::valueOf).orElse("0"));
    }
}
