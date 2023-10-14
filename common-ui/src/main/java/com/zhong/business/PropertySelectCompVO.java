package com.zhong.business;

import com.google.common.collect.Lists;
import com.zhong.CompConstants;
import com.zhong.base.*;
import com.zhong.data.SelectOptionVO;
import org.apache.commons.collections4.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PropertySelectCompVO extends BaseCompVO implements IDisabledComp, IDisabledContentComp, IDisabledCustomizeComp, ILanguageComp, IShowSearchComp, IOptionFilterPropComp {

    public PropertySelectCompVO(){
        setUiType(CompConstants.UI_TYPE_PROP_SELECT);
    }

    public void innerSetOptions(List<SelectOptionVO> options){
        put(CompConstants.FIELD_NAME_OPTIONS,options);
    }

    public void innerSetValue(SelectOptionVO selectOptionVO){
        put(CompConstants.FIELD_NAME_VALUE, selectOptionVO);
    }

    public void innerSetValues(List<SelectOptionVO> optionVOS){
        put(CompConstants.FIELD_NAME_VALUE, optionVOS);
    }

    public void innerSetFixValues(List<SelectOptionVO> optionVOS){
        put(CompConstants.FIELD_NAME_FIX_VALUE, optionVOS);
    }

    public void innerSetStrFixValues(List<String> strList){
        put(CompConstants.FIELD_NAME_FIX_VALUE, strList);
    }

    public void innerSetMultiMode(){
        put(CompConstants.FIELD_NAME_MODE,"multiple");
    }

    public void innerSetMultiMode(String mode){
        put(CompConstants.FIELD_NAME_MODE, mode);
    }


    public void innerSetUnDeletedValue(Object unDeletedValues){
        put(CompConstants.FIELD_NAME_UN_DELETED_VALUE, unDeletedValues);
    }

    public void innerSetContentUiType(String contentUiType){
        put(CompConstants.FIELD_NAME_CONTENT_UI_TYPE,contentUiType);
    }

    public void innerSetCustomizeUiType(String customizeUiType){
        put(CompConstants.FIELD_NAME_CUSTOMIZE_UI_TYPE,customizeUiType);
    }

    public List<SelectOptionVO> innerGetValue(){
        Object raw = get(CompConstants.FIELD_NAME_VALUE);
        if (raw == null){
            return null;
        }
        if (raw instanceof Map){
            SelectOptionVO optionVO = new SelectOptionVO();
            optionVO.putAll((Map<? extends String, ? extends String>) raw);
            return Lists.newArrayList(optionVO);
        }
        if (raw instanceof List){
            List optionsRawList = (List) raw;
            List<SelectOptionVO> options = new LinkedList<>();
            for (Object optionRaw:optionsRawList){
                if (optionRaw instanceof SelectOptionVO){
                    options.add((SelectOptionVO) optionRaw);
                    continue;
                }
                if (optionRaw instanceof Map){
                    SelectOptionVO optionVO = new SelectOptionVO();
                    optionVO.putAll((Map<? extends String, ? extends String>) optionRaw);
                    options.add(optionVO);
                }
            }
            return options;
        }
        return null;
    }

    public SelectOptionVO innerGetOneValue(){
        return Optional.ofNullable(innerGetValue())
                .map(values->{
                    if (CollectionUtils.isNotEmpty(values)){
                        return values.get(0);
                    }
                    return null;
                }).orElse(null);
    }

    public void innerAddContent(Boolean value){
        put(CompConstants.FIELD_NAME_ADD_CONTENT,value);
    }

    public void innerAddCustomize(Boolean value){
        put(CompConstants.FIELD_NAME_Add_CUSTOMIZE,value);
    }
}
