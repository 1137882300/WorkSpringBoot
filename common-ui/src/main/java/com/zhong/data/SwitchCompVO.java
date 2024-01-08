package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.BaseCompVO;
import com.zhong.base.IDisabledComp;
import com.zhong.base.ILinkageComp;

public class SwitchCompVO extends BaseCompVO implements IDisabledComp, ILinkageComp {

    public SwitchCompVO() {
        setUiType(CompConstants.UI_TYPE_SWITCH);
    }
}
