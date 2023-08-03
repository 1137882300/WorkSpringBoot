package com.zhong.web.base.exception.view;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

/**
 */
public interface IViewAndModeResolver {

    ModelAndView resolve(Object object);


    class DefaultViewAndModeResolver implements IViewAndModeResolver {

        private final IViewFactory factory;

        public DefaultViewAndModeResolver(IViewFactory factory) {
            this.factory = factory;
        }

        @Override
        public ModelAndView resolve(Object object) {
            ModelAndView mv = new ModelAndView();
            AbstractView view = factory.create();
            mv.setView(view);
            mv.addObject(object);
            return mv;
        }
    }

}



