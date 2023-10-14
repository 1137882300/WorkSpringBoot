package com.zhong.base;

import com.zhong.CompConstants;

import java.util.List;

public interface ITipsComp extends IComp {

    default String getTips(){
        return (String) get(CompConstants.FIELD_NAME_TIPS);
    }

    default void setTips(List<String> tips){
        put(CompConstants.FIELD_NAME_TIPS, tips);
    }
}
