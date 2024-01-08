package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.List;

public class CascaderCompVO extends BaseCompVO implements IMultiValuesComp, INameComp, ILabelComp, IRequiredComp, IDisabledComp, IVisibleComp, IPlaceholderComp, IShowSearchComp {

    public CascaderCompVO() {
        setUiType(CompConstants.UI_TYPE_CASCADER);
    }

    public void setOptions(List<LevelSelectOptionVO> options){
        put(CompConstants.FIELD_NAME_OPTIONS,options);
    }
}
