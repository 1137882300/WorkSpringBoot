package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

public class RichInputLanguageCompVO extends InputLanguageCompVO implements IDisabledComp, ILanguageComp, IMaterialComp, IRelateCompComp {

    public RichInputLanguageCompVO() {
        setUiType(CompConstants.UI_TYPE_RICH_INPUT);
        setMaterial(true);
    }

    public static RichInputLanguageCompVO castTo(BaseCompVO baseCompVO){
        if (baseCompVO.getUiType().equals(CompConstants.UI_TYPE_RICH_INPUT)){
            RichInputLanguageCompVO richInputCompVO=new RichInputLanguageCompVO();
            richInputCompVO.putAll(baseCompVO);
            return richInputCompVO;
        }
        throw new IllegalArgumentException();
    }

    public void innerSetUploadUrl(String uploadUrl){
        put(CompConstants.FIELD_NAME_UPLOAD_URL, uploadUrl);

    }


}
