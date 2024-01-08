package com.zhong.base;

import com.zhong.CompConstants;

public interface IShowSearchComp extends IComp{

    default void innerSetShowSearch(boolean showSearch){
        put(CompConstants.FIELD_NAME_SHOW_SEARCH, showSearch);
    }

}
