/**
 * @author: juzi
 * @date: 2023/6/9
 * @desc:
 */
package com.zhong.repository.multiple.second;

/**
 * @author juzi
 * @date 2023/6/9 下午 4:39
 * @description Mybatis-Plus整合多数据源
 *
 *
 * <dependency>
 *   <groupId>com.baomidou</groupId>
 *   <artifactId>dynamic-datasource-spring-boot-starter</artifactId>
 *   <version>${version}</version>
 * </dependency>
 *
 * 约定：
 * 本框架只做 切换数据源 这件核心的事情，并不限制你的具体操作，切换了数据源可以做任何CRUD。
 * ❤配置文件所有以下划线 _ 分割的数据源 首部 即为组的名称，相同组名称的数据源会放在一个组下。
 * 切换数据源可以是组名，也可以是具体数据源名称。组名在切换时采用负载均衡算法切换。
 * 默认的数据源名称为 master ，你可以通过 spring.datasource.dynamic.primary 修改。
 * 方法上的注解优先于类上注解。
 * DS支持继承抽象类上的DS，暂不支持继承接口上的DS。
 */