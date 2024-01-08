package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

import java.util.List;

public class SearchCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequestComp, IRequiredComp, IPlaceholderComp,IDisabledComp {

    public SearchCompVO(){
        setUiType(CompConstants.UI_TYPE_SEARCH);
    }

    public static SearchCompVO castTo(BaseCompVO baseCompVO){
        if (baseCompVO.getUiType().equals(CompConstants.UI_TYPE_SEARCH)){
            SearchCompVO searchCompVO = new SearchCompVO();
            searchCompVO.putAll(baseCompVO);
            return searchCompVO;
        }
        throw new IllegalArgumentException();
    }

    public String getValue(){
        return (String) get(CompConstants.FIELD_NAME_VALUE);
    }

    public void setOptions(List<SelectOptionVO> options){
        put(CompConstants.FIELD_NAME_OPTIONS,options);
    }

    public void innerSetValue(String value){
        put(CompConstants.FIELD_NAME_VALUE,value);
    }

}
