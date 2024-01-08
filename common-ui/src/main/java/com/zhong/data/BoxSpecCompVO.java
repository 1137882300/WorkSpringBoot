package com.zhong.data;


import com.zhong.CompConstants;
import com.zhong.base.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * created by zhounan on 2022/7/18
 */
public class BoxSpecCompVO extends BaseCompVO implements INameComp, ILabelComp, ILengthComp, IWidthComp, IHeightComp, IDisabledComp, IRequiredComp {

    public BoxSpecCompVO(){
        setUiType(CompConstants.UI_TYPE_BOX_SPEC);
    }

    public BoxSpecCompVO innerSetGrossWeight(BigDecimal grossWeight){
        put(CompConstants.FIELD_NAME_GROSS_WEIGHT, grossWeight);
        return this;
    }

    public BoxSpecCompVO innerSetUnit(Map<String, String> unitMap){
        put(CompConstants.FIELD_NAME_UNIT, unitMap);
        return this;
    }

    public BigDecimal innerGetGrossWeight(){

        String grossWeight = String.valueOf(get(CompConstants.FIELD_NAME_GROSS_WEIGHT));
        if(NumberUtils.isCreatable(grossWeight)){
            return new BigDecimal(grossWeight);
        }
        return null;
    }

    public BoxSpecCompVO innerSetNetWeight(BigDecimal netWeight){
        put(CompConstants.FIELD_NAME_NET_WEIGHT, netWeight);
        return this;
    }

    public BigDecimal innerGetNetWeight(){
        String netWeight = String.valueOf(get(CompConstants.FIELD_NAME_NET_WEIGHT));
        if(NumberUtils.isCreatable(netWeight)){
            return new BigDecimal(netWeight);
        }
        return null ;
    }

    public BoxSpecCompVO innerSetPcs(Long pcs){
        put(CompConstants.FIELD_NAME_PCS, pcs);
        return this;
    }

    public Long innerGetPcs(){
        String pcs = String.valueOf(get(CompConstants.FIELD_NAME_PCS));
        if(StringUtils.isNumeric(pcs)){
            return Long.valueOf(pcs);
        }
        return null;
    }

    public BoxSpecCompVO innerSetVolume(String volume){
        put(CompConstants.FIELD_NAME_VOLUME, volume);
        return this;
    }

}
