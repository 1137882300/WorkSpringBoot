package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.ActionVO;

public class SubmitActionVO extends ActionVO {

    public SubmitActionVO(){
        setType(CompConstants.ACTION_TYPE_SUBMIT);
    }

    public void setParamType(String type){
        addParam(CompConstants.FIELD_NAME_TYPE,type);
    }

    public void setParamUrl(String url){
        addParam(CompConstants.FIELD_NAME_URL,url);
    }

    public void setParamContentType(String type){
        addParam(CompConstants.FIELD_NAME_CONTENT_TYPE,type);
    }

}
