package com.zhong.business;


import com.zhong.CompConstants;
import com.zhong.CompUtils;
import com.zhong.base.BaseCompVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyValuePairVO extends HashMap<String,Object> {

    public PropertyValuePairVO(){
        put(CompConstants.FIELD_NAME_VALUE,new HashMap<>());
    }

    public void setProperty(BaseCompVO baseCompVO){
        put(CompConstants.FIELD_NAME_PROPERTY,baseCompVO);
    }

    public BaseCompVO getProperty(){
        return CompUtils.castToBaseCompVO(get(CompConstants.FIELD_NAME_PROPERTY));
    }

    public void setValue(PropertyValueVO valueVO){
        put(CompConstants.FIELD_NAME_VALUE,valueVO);
    }

    public void innerSetValues(List<PropertyValueVO> propertyValueVOS){
        put(CompConstants.FIELD_NAME_VALUE,propertyValueVOS);
    }

    public void innerDisable(){
        put(CompConstants.FIELD_NAME_DISABLE,true);
    }

    public PropertyValueVO getValue(){
        Object raw = get(CompConstants.FIELD_NAME_VALUE);
        if (raw == null){
            return null;
        }
        if (raw instanceof PropertyValueVO){
            return (PropertyValueVO) raw;
        }
        if (raw instanceof Map){
            PropertyValueVO valueVO = new PropertyValueVO();
            valueVO.putAll((Map<? extends String, ?>) raw);
            return valueVO;
        }
        return null;
    }
}
