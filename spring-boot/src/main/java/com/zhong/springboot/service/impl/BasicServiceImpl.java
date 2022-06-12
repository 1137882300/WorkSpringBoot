package com.zhong.springboot.service.impl;

import com.zhong.springboot.po.Basic;
import com.zhong.springboot.mapper.BasicMapper;
import com.zhong.springboot.service.IBasicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiao-pang
 * @since 2022-06-12
 */
@Service
public class BasicServiceImpl extends ServiceImpl<BasicMapper, Basic> implements IBasicService {

}
