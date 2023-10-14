package com.zhong.business;


import com.zhong.CompConstants;
import com.zhong.base.*;

public class PackageCompVO extends BaseCompVO implements ILabelComp, INameComp, IRequiredComp, IDisabledComp, IBatchUpdateComp,IMultiValuesComp {

    public PackageCompVO() {
        setUiType(CompConstants.UI_TYPE_PACKAGE);
    }

    public void innerSetMax(Integer max){
        put(CompConstants.FIELD_NAME_MAX, max);
    }

    public void innerSetMin(Integer min){
        put(CompConstants.FIELD_NAME_MIN, min);
    }

    public void innerSetPrecision(Integer precision){
        put(CompConstants.FIELD_NAME_PRECISION, precision);
    }
}
