package com.zhong.base;

import com.zhong.CompConstants;

public interface IToolTipComp extends IComp {

    default String getToolTip(){
        return (String) get(CompConstants.FIELD_NAME_TOOLTIPS);
    }

    default void setToolTips(String toolTips){
        put(CompConstants.FIELD_NAME_TOOLTIPS,toolTips);
    }
}
