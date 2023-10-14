package com.zhong.data;

import com.google.common.collect.Lists;
import com.zhong.CompConstants;
import com.zhong.base.*;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author zhangwenju
 * @create 2022-06-2711:46
 */
public class SelectOptionCompVO extends BaseCompVO implements INameComp, ILabelComp, IRequiredComp, IDisabledComp, IVisibleComp, IPlaceholderComp, IShowSearchComp, IBatchUpdateComp, IOptionFilterPropComp {

    public SelectOptionCompVO() {
        setUiType(CompConstants.UI_TYPE_SELECT_OPTION);
    }

    public void setOptions(List<SelectOptionVO> options) {
        put(CompConstants.FIELD_NAME_OPTIONS, options);
    }

    public void innerSetMultiMode() {
        put(CompConstants.FIELD_NAME_MODE, "multiple");
    }

    public void innerSetMaxLength(int length) {
        put(CompConstants.FIELD_NAME_MAX_LENGTH, length);
    }

    public void innerSetLabelInValue(boolean labelInValue) {
        put(CompConstants.FIELD_NAME_LABEL_IN_VALUE, labelInValue);
    }

    public void innerSetMode(String mode) {
        put(CompConstants.FIELD_NAME_MODE, mode);
    }

    public void innerSetValue(Object value) {
        put(CompConstants.FIELD_NAME_VALUE, value);
    }

    public void innerSetValues(List<String> values) {
        put(CompConstants.FIELD_NAME_VALUE, values);
    }

    public void innerSetAllowClear(Boolean allow) {
        put(CompConstants.FIELD_NAME_ALLOW_CLEAR, allow);
    }

    public List<String> innerGetValue() {
        Object raw = get(CompConstants.FIELD_NAME_VALUE);
        if (raw == null) {
            return null;
        }
        if (raw instanceof String) {
            return Lists.newArrayList((String) raw);
        }
        if (raw instanceof List) {
            return (List<String>) raw;
        }
        return null;
    }

    public Object getValue() {
        return get(CompConstants.FIELD_NAME_VALUE);
    }

    public String innerGetOneValue() {
        return Optional.ofNullable(innerGetValue())
                .map(values -> {
                    if (CollectionUtils.isNotEmpty(values)) {
                        return values.get(0);
                    }
                    return null;
                }).orElse(null);
    }
}
