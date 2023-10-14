package com.zhong;


import com.zhong.base.BaseCompVO;

import java.util.Map;
import java.util.Objects;

public class CompUtils {

    public static BaseCompVO castToBaseCompVO(Object raw) {
        if (raw == null) {
            return null;
        }
        if (raw instanceof BaseCompVO) {
            return (BaseCompVO) raw;
        }
        if (raw instanceof Map) {
            BaseCompVO compVO = new BaseCompVO();
            compVO.putAll((Map<? extends String, ?>) raw);
            return compVO;
        }
        //TODO:报错
        return null;
    }

    public static <T extends BaseCompVO> T castToComp(Class<T> compClass, BaseCompVO source) {
        T compInstance;
        try {
            compInstance = compClass.newInstance();
        } catch (Throwable e) {
            throw new RuntimeException("new instance [" + compClass.getName() + "] failed", e);
        }

        if (Objects.equals(compInstance.getUiType(), source.getUiType())) {
            compInstance.putAll(source);
            return compInstance;
        } else {
            throw new RuntimeException("uiType[" + source.getUiType() + "] cast to [" + compClass.getName() + "] failed");
        }
    }
}
