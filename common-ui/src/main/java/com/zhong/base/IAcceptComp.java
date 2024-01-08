package com.zhong.base;

import com.zhong.CompConstants;

public interface IAcceptComp extends IComp{

    default void innerSetAccept(String accept){
        put(CompConstants.FIELD_NAME_ACCEPT, accept);
    }

}
