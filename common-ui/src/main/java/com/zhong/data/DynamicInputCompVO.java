package com.zhong.data;


import com.zhong.CompConstants;

/**
 * created by zhounan on 2022/7/18
 */
public class DynamicInputCompVO extends InputCompVO{

    public void innerSetPropId(Long propId){
        put(CompConstants.FIELD_NAME_PROP_ID, propId);
    }

    public Long innerGetPropId(){
        return (Long) get(CompConstants.FIELD_NAME_PROP_ID);
    }
}
