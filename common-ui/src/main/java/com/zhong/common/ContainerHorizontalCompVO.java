package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.GroupCompVO;
import com.zhong.base.INameComp;

public class ContainerHorizontalCompVO extends GroupCompVO implements INameComp {

    public ContainerHorizontalCompVO(){
        setUiType(CompConstants.UI_TYPE_CONTAINER_HORIZONTAL);
    }

}
