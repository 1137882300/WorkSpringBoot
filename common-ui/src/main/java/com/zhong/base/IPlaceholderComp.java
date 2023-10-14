package com.zhong.base;

import com.zhong.CompConstants;

public interface IPlaceholderComp extends IComp{

    default void innerSetPlaceholder(String text){
        put(CompConstants.FIELD_NAME_PLACEHOLDER, text);
    }

}
