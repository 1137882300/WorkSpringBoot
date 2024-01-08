package com.zhong.base;


import com.zhong.CompConstants;

import java.util.List;
import java.util.Map;

public interface ILanguageComp extends IComp {

    default List<Map<String, String>> getLanguage() {
        return (List<Map<String, String>>) get(CompConstants.FIELD_NAME_LANGUAGE);
    }

    default void setLanguage(List<Map<String, String>> language) {
        put(CompConstants.FIELD_NAME_LANGUAGE, language);
    }
}
