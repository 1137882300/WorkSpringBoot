package com.zhong.base;

import com.google.common.collect.Lists;
import com.zhong.CompConstants;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionVO extends HashMap<String, Object> {

    public void setType(String type) {
        put(CompConstants.FIELD_NAME_TYPE, type);
    }

    public void addParam(String key, Object value) {
        Map<String, Object> params = (Map<String, Object>) get(CompConstants.FIELD_NAME_PARAM);
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(key, value);
        put(CompConstants.FIELD_NAME_PARAM, params);
    }

    public void innerSetParamType(String type) {
        put(CompConstants.FIELD_NAME_TYPE, type);
    }

    public void addParams(Map<String, Object> mapObj) {
        List<Map<String, Object>> mapList = (List<Map<String, Object>>) get(CompConstants.FIELD_NAME_PARAMS);
        if (CollectionUtils.isEmpty(mapList)) {
            mapList = Lists.newArrayList();
        }
        mapList.add(mapObj);
        put(CompConstants.FIELD_NAME_PARAMS, mapList);

    }
}