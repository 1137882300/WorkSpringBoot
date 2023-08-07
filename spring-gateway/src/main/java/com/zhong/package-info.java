/**
 * @author: juzi
 * @date: 2023/8/7
 * @desc:
 */
package com.zhong;

/**
 * @author juzi
 * @date 2023/8/7 上午 9:57
 * @description 反向代理
 * 微服务只接收来自网关的请求，而其它直接访问微服务本身的请求拒绝。
 * <p>
 * 这样可以极大保护微服务免受不法侵害。
 *
 * 同时在请求压力激增时，可以实施服务限流，保护微服务集群。
 */

/**
 * @author juzi
 * @date 2023/8/7 上午 10:17
 * @description 跨域问题
 *
 * 使用CORS方式。
 * <p>
 * CORS是一个W3C标准，全称是"跨域资源共享"（Cross-origin resource sharing）。
 *
 * 它允许浏览器向跨源服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。
 */