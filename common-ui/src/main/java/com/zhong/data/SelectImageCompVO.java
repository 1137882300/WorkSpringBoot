package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.HashMap;
import java.util.Map;

public class SelectImageCompVO extends BaseCompVO implements INameComp, IMultiValuesComp, ILabelComp, IRequiredComp, IAcceptComp, IDisabledComp{

    public SelectImageCompVO(){
        setUiType(CompConstants.UI_TYPE_SELECT_IMAGE);
    }

    public void innerSetRelatedCompName(String name){
        Map<String,String> props = new HashMap<>();
        props.put(CompConstants.FIELD_NAME_TYPE,"JSEXPRESSION");
        props.put(CompConstants.FIELD_NAME_VALUE,"this.elements."+name+".value");
        put(CompConstants.FIELD_NAME_LIST,props);
    }
}
