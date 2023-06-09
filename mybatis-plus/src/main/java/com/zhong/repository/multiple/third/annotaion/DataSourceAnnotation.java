package com.zhong.repository.multiple.third.annotaion;

import com.zhong.repository.multiple.third.enums.DataSourceType;

import java.lang.annotation.*;

/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc: 自定义多数据源切换注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface DataSourceAnnotation {

    DataSourceType value() default DataSourceType.LOCAL;

}
