package com.zhong.base;

import com.zhong.CompConstants;
import com.zhong.business.RequestSettingsVO;

import java.util.Map;

public interface IRequestComp extends IComp{

    default boolean getHotload(){
        return Boolean.TRUE.equals(get(CompConstants.FIELD_NAME_HOTLOAD));
    }

    default void setHotload(Boolean hotLoad){
        put(CompConstants.FIELD_NAME_HOTLOAD,hotLoad);
    }

    default void setRequest(RequestSettingsVO request){
        put(CompConstants.FIELD_NAME_REQUEST,request);
        put(CompConstants.FIELD_NAME_HOTLOAD,false);
    }

    default void innerEnableHotload(){
        put(CompConstants.FIELD_NAME_HOTLOAD,true);
    }

    default RequestSettingsVO innerGetRequest(){
        Object raw = get(CompConstants.FIELD_NAME_REQUEST);
        if (raw == null){
            return null;
        }
        if (raw instanceof RequestSettingsVO){
            return (RequestSettingsVO) raw;
        }
        if (raw instanceof Map){
            RequestSettingsVO requestSettings=new RequestSettingsVO();
            requestSettings.putAll((Map<? extends String, ?>) raw);
        }
        //TODO:是否需要抛异常
        return null;
    }

}
