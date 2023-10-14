package com.zhong.base;

import com.zhong.CompConstants;

import java.util.HashMap;


public class BaseCompVO extends HashMap<String, Object> implements IComp {

    public String getUiType() {
        return (String) super.get(CompConstants.FIELD_NAME_UI_TYPE);
    }

    protected void setUiType(String uiType) {
        super.put(CompConstants.FIELD_NAME_UI_TYPE, uiType);
    }

    @Override
    public Object get(String key) {
        return super.get(key);
    }
}
