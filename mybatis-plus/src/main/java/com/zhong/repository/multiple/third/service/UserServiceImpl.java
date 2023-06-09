package com.zhong.repository.multiple.third.service;

import com.zhong.repository.multiple.third.annotaion.DataSourceAnnotation;
import com.zhong.repository.multiple.third.enums.DataSourceType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc:
 */
@Service
public class UserServiceImpl {


    @DataSourceAnnotation(value = DataSourceType.REMOTE)
    public List<?> selectAll() {
        return null;
    }


}
