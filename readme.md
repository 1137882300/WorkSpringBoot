新建model：site-qu... 快速创建

# SpringBoot拓展
## ☟☟☟☟☟☟☟☟☟SpringBoot拓展☟☟☟☟☟☟☟☟☟
- ☛☛☛implements EnvironmentPostProcessor:用于在Spring容器启动过程中修改应用程序的环境变量或添加自定义属性。
- ☛☛☛implements SmartInitializingSingleton:当所有单例 bean 都初始化完成以后，Spring的IOC容器会回调该接口的 afterSingletonsInstantiated()方法。
- ☛☛☛implements FactoryBean:用于定义自己的Bean工厂，并在运行时动态创建Bean对象。
- ☛☛☛implements ApplicationContextAware:可以让Bean获取到ApplicationContext对象的引用，从而可以在运行时通过ApplicationContext对象访问Spring容器的其他Bean。
- ☛☛☛implements InvocationHandler:通常被用于实现动态代理
- ☛☛☛implements HandlerInterceptor:HTTP请求的拦截器，用于实现在处理程序执行之前、之后或请求完成之后拦截和处理HTTP请求
- ☛☛☛implements MethodInterceptor:
- ☛☛☛extends StaticMethodMatcherPointcut:
- ☛☛☛extends AbstractBeanFactoryPointcutAdvisor:
- ☛☛☛implements BeanPostProcessor:在每个Bean初始化，即调用setter之前和之后的后处理，用于实现初始化的逻辑控制。
- ☛☛☛implements InitializingBean:在Bean的属性都设置值后被调用，用于完成一些初始化工作。=== @PostContsuct注解
- ☛☛☛implements DisposableBean:在Bean被销毁、生命周期结束之前被调用，用于做一些销毁的收尾工作。=== @PreDestory注解
- ☛☛☛implements BeanFactoryPostProcessor:在Bean被创建之前，获取容器中Bean的定义信息，并且可以进行修改。
- ☛☛☛implements BeanFactoryAware:用于获取在该Bean被加载的过程中加载该Bean的BeanFactory，同时也可以获取这个BeanFactory中加载的其它Bean。
- ☛☛☛implements InstantiationAwareBeanPostProcessor:每个Bean的实例化(即调用构造函数)之前和之后，会分别调用该实现接口类中的这两个方法。
- ☛☛☛implements BeanNameAware:用于需要获取bean自身在容器中的id/name时。
- ☛☛☛implements SmartInstantiationAwareBeanPostProcessor
- ☛☛☛implements DestructionAwareBeanPostProcessor
- ☛☛☛implements MergedBeanDefinitionPostProcessor
- ☛☛☛implements ImportBeanDefinitionRegistrar
- ☛☛☛extends RequestBodyAdviceAdapter：用于对请求的请求体数据进行处理和修改的扩展点。
- ☛☛☛implements ResponseBodyAdvice：当控制器方法返回一个响应体时，ResponseBodyAdvice提供了一种机制，让您能够在响应体返回给客户端之前对其进行自定义处理。
- ☛☛☛implements HandlerExceptionResolver：用于处理 Web 应用程序中控制器抛出的异常。
- ☛☛☛implements ErrorViewResolver：允许开发人员自定义错误视图解析器，以便在处理Spring应用程序中的错误时进行个性化的处理。

自动生成基础代码：idea插件：easycode
实体：
```vm
##引入宏定义
$!{define.vm}
## 自定义
#set($table = $tableInfo.name)
#set($tableName = $tool.append($tableInfo.name, "Model"))
#set($tableInfo.name = $tool.append($tableInfo.name, "Model"))

##使用宏定义设置回调（保存位置与文件后缀）
#save("/model", ".java")

##使用宏定义设置包后缀
#setPackageSuffix("model")

##使用全局变量实现默认包导入
$!{autoImport.vm}
import java.io.Serializable;

##使用宏定义实现类注释信息
#tableComment("实体类")
@TableName("$tool.hump2Underline($!{table})")
public class $!{tableName} implements Serializable {
    private static final long serialVersionUID = 1L;
#foreach($column in $tableInfo.fullColumn)
    #if(${column.comment})/**
     * ${column.comment}
     */#end
    #if(${foreach.first})
    @TableId(type = IdType.AUTO)
    #end
    private $!{tool.getClsNameByFullName($column.type)} $!{column.name};
#end

#foreach($column in $tableInfo.fullColumn)
##使用宏定义实现get,set方法
#getSetMethod($column)
#end
}
```






























































































































































































































































