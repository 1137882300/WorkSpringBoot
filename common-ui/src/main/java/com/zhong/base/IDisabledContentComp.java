package com.zhong.base;

import com.zhong.CompConstants;

/**
 * @author zhangwenju
 * @create 2022-07-2815:27
 */
public interface IDisabledContentComp extends IComp {

    default void innerContentDisable(){
        put(CompConstants.FIELD_NAME_DISABLE_CONTENT,true);
    }
}
