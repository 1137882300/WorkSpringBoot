package com.zhong.business;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 属性组件
 * */
public class PropertiesCompVO extends BaseCompVO implements INameComp, IRequestComp, IRequiredComp, IDisabledContentComp, IDisabledCustomizeComp,ILabelComp {

    public PropertiesCompVO(){
        setUiType(CompConstants.UI_TYPE_PROPERTIES);
    }

    public void innerSetTemplate(BaseCompVO property){
        innerSetTemplate(property,false);
    }

    public void innerSetTemplate(BaseCompVO property,boolean disable){
        innerSetTemplate(property,null,disable);
    }

    public void innerSetTemplate(BaseCompVO property,PropertyValueVO value,boolean disable){
        PropertyValuePairVO propertyValuePairVO = new PropertyValuePairVO();
        propertyValuePairVO.setProperty(property);
        Optional.ofNullable(value).ifPresent(propertyValuePairVO::setValue);
        if(disable){
            propertyValuePairVO.innerDisable();
        }
        put(CompConstants.FIELD_NAME_TEMPLATE,propertyValuePairVO);
    }

    public void setValue(List<PropertyValuePairVO> propList){
        put(CompConstants.FIELD_NAME_VALUE,propList);
    }

    public void innerSetMaxLength(Integer length){
        put(CompConstants.FIELD_NAME_MAX_LENGTH, length);
    }

    public void innerSetUseBeta(Boolean useBeta){
        put(CompConstants.FIELD_NAME_USE_BETA, useBeta);
    }

    public void innerSetLocale(Map<String, Object> objectMap){
        put(CompConstants.FIELD_NAME_LOCALE, objectMap);
    }

    public List<PropertyValuePairVO> getValue(){

        Object raw = get(CompConstants.FIELD_NAME_VALUE);
        if(null == raw){
            return null;
        }
        if (raw instanceof List){
            List optionsRawList = (List) raw;
            List<PropertyValuePairVO> options = new LinkedList<>();
            for (Object optionRaw:optionsRawList){
                if (optionRaw instanceof PropertyValuePairVO){
                    options.add((PropertyValuePairVO) optionRaw);
                }
                if (optionRaw instanceof Map){
                    PropertyValuePairVO optionVO = new PropertyValuePairVO();
                    optionVO.putAll((Map<? extends String, ? extends String>) optionRaw);
                    options.add(optionVO);
                }
            }
            return options;
        }
        return null;
    }
}
