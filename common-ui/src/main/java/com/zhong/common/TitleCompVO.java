package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.BaseCompVO;

public class TitleCompVO extends BaseCompVO {

    public TitleCompVO(){
        setUiType(CompConstants.UI_TYPE_TITLE);
    }

    public void setContent(String content){
        put(CompConstants.FIELD_NAME_CONTENT,content);
    }

    public String getContent(){
        return (String) get(CompConstants.FIELD_NAME_CONTENT);
    }
}
