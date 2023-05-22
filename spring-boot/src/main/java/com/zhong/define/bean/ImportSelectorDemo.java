package com.zhong.define.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.Map;
import java.util.Objects;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc: 指定实现ImportSelector的类，通过AnnotationMetadata里面的属性，动态加载类。
 * AnnotationMetadata是Import注解所在的类属性（如果所在类是注解类，则延伸至应用这个注解类的非注解类为止）。
 * 需要实现selectImports方法，返回要加载的@Configuation或者具体Bean类的全限定名的String数组。
 */
public class ImportSelectorDemo implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        //被注解@EnableService 修饰的才托管bean
        Map<String, Object> map = importingClassMetadata.getAnnotationAttributes(EnableService.class.getName(), true);
        assert map != null;
        String name = (String) map.get("name");
        if (Objects.equals(name, "B")) {
            return new String[]{"com.test.ConfigB"};
        }
        //可以是@Configuration注解修饰的类，也可以是具体的Bean类的全限定名称
        return new String[]{"com.zhong.entity.EntityTest"};
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @Target(ElementType.TYPE)
    @Import(ImportSelectorDemo.class)
    @interface EnableService {
        String name();
    }

    //有地方使用这个注解，就会托管这个Bean(ImportSelectorDemo)

}
