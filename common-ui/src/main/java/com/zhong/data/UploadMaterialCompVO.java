package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;

public class UploadMaterialCompVO extends BaseCompVO implements INameComp, IMultiValuesComp, ILabelComp, IRequiredComp, IAcceptComp, IDisabledComp, IToolTipComp, ITipsComp, IModeControlComp, IRelateCompComp {

    public UploadMaterialCompVO(){
        setUiType(CompConstants.UI_TYPE_UPLOAD_MATERIAL);
        put(CompConstants.FIELD_NAME_LIST_TYPE,"picture-card");
    }

    public void setInfo(String info){
        put(CompConstants.FIELD_NAME_INFO,info);
    }

    public void setUploadUrl(String url){
        put(CompConstants.FIELD_NAME_UPLOAD_URL,url);
    }

    public void innerSetMaxNum(int num){
        put(CompConstants.FIELD_NAME_MAX_LENGTH,num);
    }

    public void innerSetDragable(boolean dragable){
        put(CompConstants.FIELD_NAME_DRAGABLE, dragable);
    }

    public void setFileSize(int size){
        put(CompConstants.FIELD_NAME_FILE_SIZE,size);
    }
}
