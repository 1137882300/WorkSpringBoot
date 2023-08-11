package com.zhong.handler.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/8/11
 * @desc:
 */
@Component
public class Test {

    @Autowired
    private Map<String, Handler> handlerMap;


    /**
     * 自动注入 handlerMap
     * handlerMap：
     * key就是 实现类的beanName
     * value就是具体实现类的实例
     */

    public void doSome() {
        //服务启动自动注入，所以这里可以直接get
        Handler handler = handlerMap.get(KeyConstants.HandlerOne);
        handler.todo();
    }

}
