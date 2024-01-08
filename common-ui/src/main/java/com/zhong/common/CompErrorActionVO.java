package com.zhong.common;

import com.google.common.collect.Maps;
import com.zhong.CompConstants;
import com.zhong.base.ActionVO;

import java.util.Map;

public class CompErrorActionVO extends ActionVO {

    public CompErrorActionVO() {
        setType(CompConstants.ACTION_TYPE_PATCH);
    }

    public void addCompError(String compName,String error){
        Map<String, Object> map = Maps.newHashMap();
        map.put(CompConstants.FIELD_NAME_NAME, compName);
        map.put(CompConstants.FIELD_NAME_ERROR, error);
        addParams(map);
    }


}
