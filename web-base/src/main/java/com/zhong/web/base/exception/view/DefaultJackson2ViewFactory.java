package com.zhong.web.base.exception.view;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.servlet.view.AbstractView;

/**
 */
@Slf4j
public class DefaultJackson2ViewFactory implements IViewFactory {

    private final ObjectProvider<IModelAdvice> advice;

    public DefaultJackson2ViewFactory(ObjectProvider<IModelAdvice> advice) {
        this.advice = advice;
    }

    @Override
    public AbstractView create() {
        CustomMappingJackson2JsonView jsonView = new CustomMappingJackson2JsonView();
        jsonView.setModelAdvice(this.advice.getIfUnique());
        return jsonView;
    }
}
