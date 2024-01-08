package com.zhong.business;


import com.zhong.CompConstants;

import java.util.HashMap;
import java.util.Map;

public class RequestSettingsVO extends HashMap<String,Object> {

    public RequestSettingsVO(){}

    public RequestSettingsVO(String type, String url){
        setType(type);
        setUrl(url);
    }

    public String getType(){
        return (String) get(CompConstants.FIELD_NAME_TYPE);
    }

    public RequestSettingsVO setType(String type){
        put(CompConstants.FIELD_NAME_TYPE,type);
        return this;
    }

    public String getUrl(){
        return (String) get(CompConstants.FIELD_NAME_URL);
    }

    public RequestSettingsVO setUrl(String url){
        put(CompConstants.FIELD_NAME_URL,url);
        return this;
    }

    public Map<String,String> getData(){
        return (Map<String,String>) get(CompConstants.FIELD_NAME_DATA);
    }

    public RequestSettingsVO setData(Map<String,String> data){
        put(CompConstants.FIELD_NAME_DATA,data);
        return this;
    }
}
