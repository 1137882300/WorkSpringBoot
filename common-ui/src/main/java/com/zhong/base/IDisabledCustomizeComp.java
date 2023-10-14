package com.zhong.base;


import com.zhong.CompConstants;

/**
 * @author zhangwenju
 * @create 2022-07-2815:31
 */
public interface IDisabledCustomizeComp extends IComp {

    default void innerCustomizeDisable(){
        put(CompConstants.FIELD_NAME_DISABLE_CUSTOMIZE,true);
    }
}
