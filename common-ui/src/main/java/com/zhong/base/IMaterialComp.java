package com.zhong.base;

import com.zhong.CompConstants;

public interface IMaterialComp extends IComp {

    default void setMaterial(Boolean value){
        put(CompConstants.FIELD_NAME_UPLOAD_MATERIAL,value);
    }
}
