package com.zhong.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author: juzi
 * @date: 2023/8/7
 * @desc: 有时候SpringCloudGateWay提供的过滤器工厂不能满足自己的要求。
 * 可能有时候需要在过滤时做一些其它的逻辑操作。
 * 那么这时候可以选择使用java代码自定义全局过滤器。
 *
 * 过滤器的执行顺序为：默认过滤器（default-filters） → 当前路由过滤器（filters） → 全局过滤器（GlobalFilter）。
 */
@Component
public class GateWayFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 1.获取请求参数
        //1.这里的request并不是servlet中的request
        //2.返回值是一个多键的map集合、也就是说这个map集合的键可以重复
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        // 2.获取userName参数
        String userName = params.getFirst("userName");
        // 3.校验
        if ("root".equals(userName)) {
            // 放行
            return chain.filter(exchange);
        }
        // 4.拦截
        // 4.1.禁止访问，设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        // 4.2.结束处理
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
