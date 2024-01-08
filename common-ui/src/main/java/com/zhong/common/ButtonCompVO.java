package com.zhong.common;

import com.zhong.CompConstants;
import com.zhong.base.ActionVO;
import com.zhong.base.BaseCompVO;

import java.util.List;

public class ButtonCompVO extends BaseCompVO {

    public ButtonCompVO() {
        setUiType(CompConstants.UI_TYPE_BUTTON);
    }

    public void setContent(String content){
        put(CompConstants.FIELD_NAME_CONTENT,content);
    }

    public void setTitle(String title){
        put(CompConstants.FIELD_NAME_TITLE, title);
    }

    public void setActions(List<ActionVO> actions){
        put(CompConstants.FIELD_NAME_ACTIONS,actions);
    }

    public void setType(String type){
        put(CompConstants.FIELD_NAME_TYPE, type);
    }

    public void setName(String name){
        put(CompConstants.FIELD_NAME_NAME, name);
    }

    public void setHref(String href){
        put(CompConstants.FIELD_NAME_HREF, href);
    }

    public void setTarget(String target){
        put(CompConstants.FIELD_NAME_TARGET, target);
    }
}
