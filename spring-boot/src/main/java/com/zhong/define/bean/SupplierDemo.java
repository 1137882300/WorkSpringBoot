package com.zhong.define.bean;

import com.zhong.entity.EntityTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc:
 */
public class SupplierDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(EntityTest.class, EntityTest::new);

        context.refresh();

        System.out.println(context.getBean("entityTest"));
    }
}
