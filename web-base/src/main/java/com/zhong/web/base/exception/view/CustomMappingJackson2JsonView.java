package com.zhong.web.base.exception.view;


import com.zhong.common.ApiResponse;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * MappingJackson2JsonView：将Java对象转换为JSON格式的数据输出。
 * 可以重写父类的方法来自定义JSON输出的行为，比如修改JSON的格式化方式、添加自定义的字段或属性等。
 */
public class CustomMappingJackson2JsonView extends MappingJackson2JsonView implements DecoratorView<ApiResponse<?>> {

    private IModelAdvice advice;

    @Override
    protected Object filterModel(Map<String, Object> model) {
        Object obj = super.filterModel(model);
        Object response = resolve(obj);
        if (response != null) {
            return processBody(response);
        }
        return obj;
    }

    private Object processBody(Object response) {
        if (advice != null) {
            return advice.processBody(response);
        }
        return response;
    }

    @SuppressWarnings({"unchecked"})
    private Object resolve(Object obj) {
        if (obj instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) obj;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                final Object value = entry.getValue();
                if (value instanceof ApiResponse) {
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public void setModelAdvice(IModelAdvice advice) {
        this.advice = advice;
    }
}
