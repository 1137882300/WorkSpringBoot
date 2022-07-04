package com.zhong.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhong.springboot.mapper.BasicMapper;
import com.zhong.springboot.po.Basic;
import com.zhong.springboot.service.IBasicService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xiao-pang
 * @since 2022-06-12
 */
@Service
public class BasicServiceImpl extends ServiceImpl<BasicMapper, Basic> implements IBasicService {

    @Resource
    private BasicMapper basicMapper;

    public void test() throws Exception {


    }

    /**
     * condition:true 才缓存
     */
    @Cacheable(cacheNames = "user", key = "#id", condition = "#id > 0")
    @Override
    public String findById(Long id) {
        log.warn("findById 执行了");
        return "findById id = " + id;
    }

    /**
     * @CachePut 可以使用在：类/方法
     * 加了这个注解：无论在缓存中是否存在缓存，程序都会执行，并将查到的结果添加指定的缓存中。
     */
    @CachePut(value = "user")
    public String findByDb(Long id) {
        log.warn("findByDb 执行了");
        return "findByDb id = " + id;
    }


}
