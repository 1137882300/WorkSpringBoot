package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.BaseCompVO;
import com.zhong.base.IVisibleComp;

public class TextCompVO extends BaseCompVO implements IVisibleComp {

    public TextCompVO() {
        setUiType(CompConstants.UI_TYPE_TEXT);
    }

}
