package com.zhong.base;

import com.zhong.CompConstants;

public interface IOptionFilterPropComp extends IComp{

    default void innerSetOptionFilterProp(String label){
        put(CompConstants.FIELD_NAME_OPTION_FILTER_PROP, label);
    }

}
