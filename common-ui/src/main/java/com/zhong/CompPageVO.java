package com.zhong;


import com.zhong.base.BaseCompVO;
import com.zhong.base.GroupCompVO;
import com.zhong.common.ContainerFixedCompVO;
import com.zhong.common.ContainerHorizontalCompVO;
import com.zhong.common.ContainerVerticalCompVO;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 组件化协议页面
 */
@Getter
public class CompPageVO {

    private Map<String, Object> context;

    private List<BaseCompVO> element;

    public CompPageVO setContext(Map<String, Object> context) {
        this.context = context;
        return this;
    }

    public CompPageVO setElement(List<BaseCompVO> element) {
        this.element = element;
        return this;
    }

    public void traversalAllComps(Consumer<BaseCompVO> consumer) {
        traversalAllComps(element, consumer);
    }

    private void traversalAllComps(List<BaseCompVO> compVOList, Consumer<BaseCompVO> consumer) {
        if (CollectionUtils.isNotEmpty(compVOList)) {
            for (BaseCompVO compVO : compVOList) {
                consumer.accept(compVO);
                //如果是组组件则向下遍历
                if (compVO instanceof GroupCompVO) {
                    traversalAllComps(((GroupCompVO) compVO).getElement(), consumer);
                } else {
                    //尝试匹配uiType
                    switch (compVO.getUiType()) {
                        case CompConstants.UI_TYPE_CONTAINER_HORIZONTAL:
                            traversalAllComps(CompUtils.castToComp(ContainerHorizontalCompVO.class, compVO).getElement(), consumer);
                            break;
                        case CompConstants.UI_TYPE_CONTAINER_FIXED:
                            traversalAllComps(CompUtils.castToComp(ContainerFixedCompVO.class, compVO).getElement(), consumer);
                            break;
                        case CompConstants.UI_TYPE_CONTAINER_VERTICAL:
                            traversalAllComps(CompUtils.castToComp(ContainerVerticalCompVO.class, compVO).getElement(), consumer);
                            break;
                    }
                }
            }
        }
    }
}
