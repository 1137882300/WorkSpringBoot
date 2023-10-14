package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.BaseCompVO;
import com.zhong.base.INameComp;

import java.util.List;

public class AlertCompVO extends BaseCompVO implements INameComp {

    public AlertCompVO(){
        setUiType(CompConstants.UI_TYPE_ALERT);
    }

    public void setMessage(List<String> lines){
        put(CompConstants.FIELD_NAME_MESSAGE,lines);
    }

    public void setType(String type){
        put(CompConstants.FIELD_NAME_TYPE,type);
    }
}
