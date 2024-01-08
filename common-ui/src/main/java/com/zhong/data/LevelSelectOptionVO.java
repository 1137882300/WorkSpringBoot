package com.zhong.data;

import com.zhong.CompConstants;

import java.util.HashMap;
import java.util.List;

/**
 * 层级选择
 * */
public class LevelSelectOptionVO extends HashMap<String,Object> {

    public void setLabel(String label){
        put(CompConstants.FILED_NAME_LABEL,label);
    }

    public void setValue(String value){
        put(CompConstants.FIELD_NAME_VALUE,value);
    }

    public String getValue(){
        return (String) get(CompConstants.FIELD_NAME_VALUE);
    }

    public void setChildren(List<LevelSelectOptionVO> children){
        put(CompConstants.FIELD_NAME_CHILDREN,children);
    }
}
