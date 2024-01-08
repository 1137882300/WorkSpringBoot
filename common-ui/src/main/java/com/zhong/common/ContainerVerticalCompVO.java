package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.GroupCompVO;
import com.zhong.base.INameComp;

public class ContainerVerticalCompVO extends GroupCompVO implements INameComp {

    public ContainerVerticalCompVO(){
        setUiType(CompConstants.UI_TYPE_CONTAINER_VERTICAL);
    }

}
