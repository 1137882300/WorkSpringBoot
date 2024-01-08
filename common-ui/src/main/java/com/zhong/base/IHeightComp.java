package com.zhong.base;

import com.zhong.CompConstants;

import java.util.Optional;

public interface IHeightComp extends IComp {

    default String getHeight(){
        return Optional.ofNullable(get(CompConstants.FIELD_NAME_HEIGHT)).map(String::valueOf).orElse("0");
    }

    default void setHeight(Float height){
        put(CompConstants.FIELD_NAME_HEIGHT, Optional.ofNullable(height).map(String::valueOf).orElse("0"));
    }
}
