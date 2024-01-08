package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class InputNumberCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequiredComp, IDisabledComp, IPlaceholderComp, IBatchUpdateComp, IVisibleComp {

    public String getValue(){
        return Optional.ofNullable(get(CompConstants.FIELD_NAME_VALUE))
                .map(String::valueOf)
                .orElse(StringUtils.EMPTY);
    }

    public Integer getIntegerValue(){
        Object value = get(CompConstants.FIELD_NAME_VALUE);
        return Objects.isNull(value)? null:Integer.valueOf(value.toString());
    }

    public InputNumberCompVO setValue(String value){
        put(CompConstants.FIELD_NAME_VALUE,value);
        return this;
    }

    public InputNumberCompVO(){
        setUiType(CompConstants.UI_TYPE_INPUT_NUMBER);
    }

    public void innerSetMaxLength(int length){
        put(CompConstants.FIELD_NAME_MAX_LENGTH, length);
    }

    public void innerSetMin(Integer min){
        put(CompConstants.FIELD_NAME_MIN, min);
    }

    public void innerSetPrecision(Integer precision){
        put(CompConstants.FIELD_NAME_PRECISION, precision);
    }

    public void innerSetSuffix(BaseCompVO baseCompVO){
        put(CompConstants.FIELD_NAME_SUFFIX,baseCompVO);
    }

    public void innerSetSuffixList(List<BaseCompVO> baseCompVOList){
        put(CompConstants.FIELD_NAME_SUFFIXS, baseCompVOList);
    }
}
