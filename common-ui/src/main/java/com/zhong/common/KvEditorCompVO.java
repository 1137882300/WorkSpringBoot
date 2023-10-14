package com.zhong.common;


import com.zhong.CompConstants;
import com.zhong.base.*;

/**
 * @author: zhuang wei
 * @date: 2022/7/18
 **/
public class KvEditorCompVO extends GroupCompVO implements INameComp, ILabelComp, IRequiredComp, IDisabledComp {

    public KvEditorCompVO(){
        setUiType(CompConstants.UI_TYPE_KV_EDITOR);
    }


}
