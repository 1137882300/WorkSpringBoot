package com.zhong.web.base.exception.view;

/**
 *
 */
public interface IModelAdvice {

    Object processBody(Object object);

    class DefaultModelAdvice implements IModelAdvice {

        @Override
        public Object processBody(Object object) {
            return object;
        }
    }

}
