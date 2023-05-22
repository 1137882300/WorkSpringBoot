package com.zhong.define.bean;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: juzi
 * @date: 2023/5/22
 * @desc: @ComponentScan 注解用于指定 Spring 容器扫描哪些包，发现哪些 Bean，并把它们加载到 Spring 容器中。
 * 使用 @ComponentScan 注解可以自动将指定包(及其子包)下的带有 @Component、@Service、@Repository、@Controller 等注解的类加载到 Spring 容器中作为 Bean。
 * <p>
 * 但值得注意的是，和 @Configuration 注解、@Bean 注解等方式不同，@ComponentScan 本身并不会在 Spring 容器中创建新的 Bean，而是在其他配置类或组件类中使用。
 * 在使用了 @ComponentScan 注解后，Spring 容器仍然需要其他配置类或组件类来定义具体的 Bean。
 * @ComponentScan 注解在实际使用过程中，通常会和其他的 Spring 容器组件(如 @Configuration 注解或者 @Bean 注解)一起使用，来定义具体的 Bean。
 */

@Configuration
@ComponentScan("com.zhong")
public class ComponentScanDemo {


}
