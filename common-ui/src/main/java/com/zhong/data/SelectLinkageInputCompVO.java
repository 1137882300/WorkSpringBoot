package com.zhong.data;

import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.List;

public class SelectLinkageInputCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequiredComp, IAcceptComp, IDisabledComp, IOptionFilterPropComp{

    public SelectLinkageInputCompVO(){
        setUiType(CompConstants.UI_TYPE_SELECT_LINKAGE_INPUT);
    }

    public void setOptions(List<SelectOptionVO> options){
        put(CompConstants.FIELD_NAME_OPTIONS,options);
    }

    public void innerSetWidth(int width){
        put(CompConstants.FIELD_NAME_WIDTH,width);
    }
}
