package com.zhong.support.support;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.zhong.support.support.impl.*;

import java.util.List;

/**
 * @author juzi
 * @date 2023/8/2 上午 9:50
 * @description SQL 注射器
 */
public class SqlInjectorAdapter extends DefaultSqlInjector {
    public SqlInjectorAdapter() {
    }

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectAll());
        methodList.add(new InsertList());
        methodList.add(new SoftDeleteById());
        methodList.add(new ExistById());
        methodList.add(new SoftSelectById());
        methodList.add(new SoftSelectBatchIds());
        methodList.add(new SoftExistById());
        methodList.add(new SoftSelectAll());
        return methodList;
    }
}
