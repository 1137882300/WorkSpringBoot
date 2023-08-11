package com.zhong.handler.v1;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/5/16
 * @desc:
 */
@Component
public class HandlerHolder {

    private final Map<Integer, Handler> handlers = Maps.newHashMap();

    public void putHandler(Integer channelCode, Handler handler) {
        handlers.put(channelCode, handler);
    }

    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }

}

