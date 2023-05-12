package com.zhong.controller;


import com.alibaba.nacos.shaded.com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.channels.Pipe;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiao-pang
 * @since 2022-06-12
 */

@Api(tags = "api接口测试")
@RestController
@RequestMapping("/basic")
@Slf4j
public class BasicController {


    @GetMapping(
            value = "/queryById/{id}",
            headers = {"content-type=application/json"}
    )
    public String queryById(@PathVariable Long id) {
        return null;
    }

    @GetMapping(
            value = "/health"
    )
    public String health() {
        log.info("health");
        return "ok";
    }

}

