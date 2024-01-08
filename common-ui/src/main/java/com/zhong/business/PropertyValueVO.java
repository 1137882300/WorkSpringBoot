package com.zhong.business;


import com.zhong.CompConstants;
import com.zhong.CompUtils;
import com.zhong.base.BaseCompVO;

import java.util.HashMap;

public class PropertyValueVO extends HashMap<String,Object> {

    public void setContent(BaseCompVO compVO){
        put(CompConstants.FIELD_NAME_CONTENT,compVO);
    }

    public BaseCompVO getContent(){
        return CompUtils.castToBaseCompVO(get(CompConstants.FIELD_NAME_CONTENT));
    }

    public void setCustomize(BaseCompVO compVO){
        put(CompConstants.FIELD_NAME_CUSTOMIZE,compVO);
    }

    public BaseCompVO getCustomize(){
        return CompUtils.castToBaseCompVO(get(CompConstants.FIELD_NAME_CUSTOMIZE));
    }

}
