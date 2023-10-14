package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.GroupCompVO;
import com.zhong.base.INameComp;

public class ContainerFixedCompVO extends GroupCompVO implements INameComp {

    public ContainerFixedCompVO(){
        setUiType(CompConstants.UI_TYPE_CONTAINER_FIXED);
    }

}
