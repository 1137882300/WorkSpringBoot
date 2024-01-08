package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.List;

public class TreeSelectCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequestComp, IRequiredComp {

    public TreeSelectCompVO(){
        setUiType(CompConstants.UI_TYPE_TREE_SELECT);
    }

    public void setOptions(List<LevelSelectOptionVO> options){
        put(CompConstants.FIELD_NAME_OPTIONS,options);
    }
}
