package com.zhong.base;

import com.zhong.CompConstants;

import java.util.List;

/**
 * @author: zhuang wei
 * @date: 2022/9/1
 **/
public interface IRelateCompComp extends IComp{

    default void setRelateComp(List<String> comps){
        put(CompConstants.FIELD_NAME_RELATE_COMP,comps);
    }

}
