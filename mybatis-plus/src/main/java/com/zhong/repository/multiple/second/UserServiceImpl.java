package com.zhong.repository.multiple.second;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc: slave 是数据源组，slave_1是slave组里的数据源
 */

@Service
@DS("slave")
public class UserServiceImpl {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<?> selectAll() {
        return jdbcTemplate.queryForList("select * from user");
    }

    @DS("slave_1")
    public List<?> selectByCondition() {
        return jdbcTemplate.queryForList("select * from user where age >10");
    }


}
