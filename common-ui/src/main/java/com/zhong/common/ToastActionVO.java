package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.ActionVO;

import java.util.List;

public class ToastActionVO extends ActionVO {

    public ToastActionVO(){
        setType(CompConstants.ACTION_TYPE_TOAST);
    }

    public ToastActionVO setParamType(String type){
        addParam(CompConstants.FIELD_NAME_TYPE,type);
        return this;
    }

    public ToastActionVO setParamUrl(String type, String url){
        addParam(CompConstants.FIELD_NAME_URL, url);
        innerSetParamType(type);
        return this;
    }

    public ToastActionVO setParamContent(String content){
        addParam(CompConstants.FIELD_NAME_CONTENT,content);
        return this;
    }


    public void innerSetActions(List<ActionVO> actions){
        put(CompConstants.FIELD_NAME_ACTIONS,actions);
    }

    public void innerSetTitle(String title){
        put(CompConstants.FIELD_NAME_TITLE, title);
    }

    public void innerSetContent(String content){
        put(CompConstants.FIELD_NAME_CONTENT, content);
    }
}
