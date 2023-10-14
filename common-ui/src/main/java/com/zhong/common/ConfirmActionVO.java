package com.zhong.common;

import com.google.common.collect.Maps;
import com.zhong.CompConstants;
import com.zhong.base.ActionVO;

import java.util.List;
import java.util.Map;

/**
 * 弹窗确认
 */
public class ConfirmActionVO extends ActionVO {

    public ConfirmActionVO(){
        setType(CompConstants.ACTION_TYPE_CONFIRM);
    }


    public ConfirmActionVO setParam(String type, String url){
        addParam(CompConstants.FIELD_NAME_URL, url);
        return this;
    }




    public void innerSetActions(List<ActionVO> actions){
        Map<String, Object> actionsMap = Maps.newHashMap();
        actionsMap.put(CompConstants.FIELD_NAME_ACTIONS,actions);
        addParam(CompConstants.FIELD_NAME_ACTIONS,actions);
    }

    public void innerSetTitle(String title){
        put(CompConstants.FIELD_NAME_TITLE, title);
    }

    public void innerSetContent(String content){
        put(CompConstants.FIELD_NAME_CONTENT, content);
    }

    class InnerParam{



    }
}
