package com.zhong.base;

import com.google.common.collect.Lists;
import com.zhong.CompConstants;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface IMultiValuesComp extends IComp {

    default List<String> getValue() {
        return (List<String>) Optional.ofNullable(get(CompConstants.FIELD_NAME_VALUE))
                .map(mList -> ((List) mList).stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList())
                )
                .orElse(Lists.newLinkedList());
    }

    default void setValue(List<String> values) {
        put(CompConstants.FIELD_NAME_VALUE, values);
    }
}
