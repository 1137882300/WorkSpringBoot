package com.zhong.repository.multiple.third.aspect;

import com.zhong.repository.multiple.third.annotaion.DataSourceAnnotation;
import com.zhong.repository.multiple.third.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc:
 */
public class DynamicDataSourceAspect {


    @Pointcut("@annotation(com.zhong.repository.multiple.third.annotaion.DataSourceAnnotation)")
    public void dsPointCut() {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        DataSourceAnnotation dataSourceAnnotation = method.getAnnotation(DataSourceAnnotation.class);
        if (dataSourceAnnotation != null) {
            DynamicDataSourceContextHolder.setDateSourceType(dataSourceAnnotation.value().name());
        }
        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDateSourceType();
        }
    }


//    /**
//     * 切换数据库
//     */
//    @Before("@annotation(dataSourceAnnotation)")
//    public void changeDataSource(JoinPoint point, DataSourceAnnotation dataSourceAnnotation) {
//        Object[] methodArgs = point.getArgs();
//        String dsId = methodArgs[methodArgs.length - 1].toString();
//
//        if (!DynamicDataSourceContextHolder.existDateSource(dsId)) {
//
//            return;
//        } else {
//            DynamicDataSourceContextHolder.setDateSourceType(dsId);
//        }
//    }
//
//    /**
//     * 销毁数据源  在所有的方法执行执行完毕后
//     */
//    @After("@annotation(dataSourceAnnotation)")
//    public void destroyDataSource(JoinPoint point, DataSourceAnnotation dataSourceAnnotation) {
//        DynamicDataSourceContextHolder.clearDateSourceType();
//    }
}
