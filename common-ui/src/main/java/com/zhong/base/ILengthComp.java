package com.zhong.base;

import com.zhong.CompConstants;

import java.util.Optional;

public interface ILengthComp extends IComp {

    default String getLength(){
        return Optional.ofNullable(get(CompConstants.FIELD_NAME_LENGTH)).map(String::valueOf).orElse("0");
    }

    default void setLength(Float length){
        put(CompConstants.FIELD_NAME_LENGTH, Optional.ofNullable(length).map(String::valueOf).orElse("0"));
    }
}
