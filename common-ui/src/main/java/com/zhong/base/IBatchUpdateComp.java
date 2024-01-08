package com.zhong.base;

import com.zhong.CompConstants;

public interface IBatchUpdateComp extends IComp{

    default void innerSetBatchUpdate(Boolean batchUpdate){
        put(CompConstants.FIELD_NAME_BATCH_UPDATE, batchUpdate);
    }

}
