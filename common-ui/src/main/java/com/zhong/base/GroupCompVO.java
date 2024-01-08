package com.zhong.base;

import com.zhong.CompConstants;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GroupCompVO extends BaseCompVO {

    public List<BaseCompVO> getElement(){
        Object raw = get(CompConstants.FIELD_NAME_ELEMENT);
        if(null == raw){
            return null;
        }
        if (raw instanceof List){
            List baseCompRawList = (List) raw;
            List<BaseCompVO> baseCompList = new LinkedList<>();
            for (Object baseCompRaw:baseCompRawList){
                if (baseCompRaw instanceof BaseCompVO){
                    baseCompList.add((BaseCompVO) baseCompRaw);
                    continue;
                }
                if (baseCompRaw instanceof Map){
                    BaseCompVO baseCompVO = new BaseCompVO();
                    baseCompVO.putAll((Map<? extends String, Object>) baseCompRaw);
                    baseCompList.add(baseCompVO);
                }
            }
            return baseCompList;
        }
        return null;
    }

    public void setElement(List<BaseCompVO> childComps){
        put(CompConstants.FIELD_NAME_ELEMENT,childComps);
    }

    public void addComp(BaseCompVO childComp){
        List<BaseCompVO> childComps = getElement();
        if (childComps == null){
            childComps = new LinkedList<>();
        }
        childComps.add(childComp);
        setElement(childComps);
    }
}
