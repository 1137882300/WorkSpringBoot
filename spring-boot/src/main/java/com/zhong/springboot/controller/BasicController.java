package com.zhong.springboot.controller;


import com.zhong.springboot.service.IBasicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xiao-pang
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/basic")
public class BasicController {

    @Resource
    private IBasicService basicService;

    @GetMapping(
            value = "/queryById/{id}",
            headers = {"content-type=application/json"}
    )
    public String queryById(@PathVariable Long id) {
        return basicService.findById(id);
    }

}

