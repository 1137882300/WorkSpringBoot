/**
 * @author: juzi
 * @date: 2023/6/30
 * @desc:
 */
package com.zhong.limiting;

/*
 * 限流的方式：
 * 1. guava包提供的令牌桶算法
 * 2. redis+lua脚本
 * 3. sentinel原生方式
 * 4. java提供的semaphore
 * 5. dubbo 框架级限流
 * 6. hystrix 框架级限流
 * 7. sentinel 框架级限流
 * 抛开网关层的限流先不说，在微服务应用中，考虑到技术栈的组合，团队人员的开发水平，以及易维护性等因素，一个比较通用的做法是，
 * 利用AOP技术+自定义注解实现对特定的方法或接口进行限流，下面基于这个思路来分别介绍下几种常用的限流方案的实现。
 */