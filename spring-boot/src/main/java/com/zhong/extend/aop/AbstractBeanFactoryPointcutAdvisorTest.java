package com.zhong.extend.aop;

import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc: 可以自定义一个基于Bean工厂的切面Advisor。
 * 在子类中实现必要的方法，并添加适合你需求的逻辑代码。然后，将该切面Advisor配置为Spring AOP中的一个组件，应用于目标类的方法拦截和增强中。
 * 通知器实现
 */
public class AbstractBeanFactoryPointcutAdvisorTest {

    public static class MyBeanFactoryPointcutAdvisor extends AbstractBeanFactoryPointcutAdvisor {
        private StaticMethodMatcherPointcutTest.MyStaticMethodMatcherPointcut myStaticMethodMatcherPointcut;
        private MethodInterceptorTest.MyMethodInterceptor myMethodInterceptor;

        public void setPointcut(StaticMethodMatcherPointcutTest.MyStaticMethodMatcherPointcut pointcut) {
            this.myStaticMethodMatcherPointcut = pointcut;
        }

        public void setMethodInterceptor(MethodInterceptorTest.MyMethodInterceptor interceptor) {
            this.myMethodInterceptor = interceptor;
        }

        @Override
        public Pointcut getPointcut() {
            return myStaticMethodMatcherPointcut;
        }

        @Override
        public MethodInterceptorTest.MyMethodInterceptor getAdvice() {
            return myMethodInterceptor;
        }

    }

    public static class MyService {
        public void performAction1() {
            System.out.println("Performing action 1");
        }

        public void performAction2() {
            System.out.println("Performing action 2");
        }
    }

    public static void main(String[] args) {
        // 创建目标类
        MyService myService = new MyService();

        // 创建方法拦截器
        MethodInterceptorTest.MyMethodInterceptor interceptor = new MethodInterceptorTest.MyMethodInterceptor();

        // 创建静态方法切点
        StaticMethodMatcherPointcutTest.MyStaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcutTest.MyStaticMethodMatcherPointcut();

        // 创建切面Advisor
        MyBeanFactoryPointcutAdvisor advisor = new MyBeanFactoryPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setMethodInterceptor(interceptor);

        // 创建代理工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(myService);
        proxyFactory.addAdvisor(advisor);

        // 获取代理对象
        MyService proxy = (MyService) proxyFactory.getProxy();

        // 调用目标方法
        proxy.performAction1();
        proxy.performAction2();
    }
}
