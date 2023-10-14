package com.zhong.base;

import com.zhong.CompConstants;
import com.zhong.data.SelectOptionVO;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface IOptionsComp extends IComp{

    default List<SelectOptionVO> getOptions(){
        Object raw = get(CompConstants.FIELD_NAME_OPTIONS);
        if (raw == null){
            return null;
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
        //TODO:是否需要抛异常
        return null;
    }

    default void setOptions(List<SelectOptionVO> options){
        put(CompConstants.FIELD_NAME_OPTIONS,options);
    }
}
