package com.zhong.data;

import com.google.common.collect.Lists;
import com.zhong.CompConstants;
import com.zhong.base.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SelectCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequiredComp, IDisabledComp, IVisibleComp, IPlaceholderComp, IShowSearchComp, IBatchUpdateComp, IOptionFilterPropComp, IToolTipComp {

    public SelectCompVO() {
        setUiType(CompConstants.UI_TYPE_SELECT);
    }

    public void setOptions(List<SelectOptionVO> options){
        put(CompConstants.FIELD_NAME_OPTIONS,options);
    }

    public List<Map> innerGetOptions(){
        return (List<Map>) get(CompConstants.FIELD_NAME_OPTIONS);
    }

    public void innerSetMultiMode(){
        put(CompConstants.FIELD_NAME_MODE,"multiple");
    }

    public void innerSetMaxLength(int length){
        put(CompConstants.FIELD_NAME_MAX_LENGTH, length);
    }

    public void innerSetLabelInValue(boolean labelInValue){
        put(CompConstants.FIELD_NAME_LABEL_IN_VALUE, labelInValue);
    }

    public void innerSetMode(String mode){
        put(CompConstants.FIELD_NAME_MODE, mode);
    }

    public void innerSetValue(String value){
        put(CompConstants.FIELD_NAME_VALUE,value);
    }

    public void innerSetValues(List<String> values){
        put(CompConstants.FIELD_NAME_VALUE,values);
    }

    public void innerSetAllowClear(Boolean allow){
        put(CompConstants.FIELD_NAME_ALLOW_CLEAR, allow);
    }

    public void innerSetWidth(int width){
        put(CompConstants.FIELD_NAME_WIDTH,width);
    }

    public List<String> innerGetValue(){
        Object raw = get(CompConstants.FIELD_NAME_VALUE);
        if (raw == null){
            return null;
        }
        if (raw instanceof String){
            return Lists.newArrayList((String)raw);
        }
        if (raw instanceof List){
            return (List<String>) raw;
        }
        return null;
    }

    public String innerGetOneValue(){
        return Optional.ofNullable(innerGetValue())
                .map(values->{
                    if (CollectionUtils.isNotEmpty(values)){
                        return values.get(0);
                    }
                    return null;
                }).orElse(null);
    }
}
