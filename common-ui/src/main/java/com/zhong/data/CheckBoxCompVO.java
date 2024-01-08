package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

public class CheckBoxCompVO extends BaseCompVO implements INameComp, IRequiredComp, ILabelComp, IDisabledComp, IMultiValuesComp, IOptionsComp {

    public CheckBoxCompVO() {
        setUiType(CompConstants.UI_TYPE_CHECK_BOX);
    }

    public static CheckBoxCompVO castTo(BaseCompVO baseCompVO){
        if (baseCompVO.getUiType().equals(CompConstants.UI_TYPE_CHECK_BOX)){
            CheckBoxCompVO checkBoxCompVO = new CheckBoxCompVO();
            checkBoxCompVO.putAll(baseCompVO);
            return checkBoxCompVO;
        }
        throw new IllegalArgumentException();
    }

    public CheckBoxCompVO innerSetHasCustomValue(boolean hasCustomVal){
        put(CompConstants.FIELD_NAME_HAS_CUSTOM_VAL, hasCustomVal);
        return this;
    }

}
