package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

public class RichInputCompVO extends InputCompVO implements IDisabledComp, ILanguageComp, IRequiredComp, IMaterialComp {

    public RichInputCompVO() {
        setUiType(CompConstants.UI_TYPE_RICH_INPUT);
        setMaterial(true);
    }

    public static RichInputCompVO castTo(BaseCompVO baseCompVO){
        if (baseCompVO.getUiType().equals(CompConstants.UI_TYPE_RICH_INPUT)){
            RichInputCompVO richInputCompVO=new RichInputCompVO();
            richInputCompVO.putAll(baseCompVO);
            return richInputCompVO;
        }
        throw new IllegalArgumentException();
    }

    public void innerSetUploadUrl(String uploadUrl){
        put(CompConstants.FIELD_NAME_UPLOAD_URL, uploadUrl);

    }


}
