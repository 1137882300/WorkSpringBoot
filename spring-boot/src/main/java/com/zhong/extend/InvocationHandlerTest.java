package com.zhong.extend;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.stream.IntStream;

/**
 * @author: juzi
 * @date: 2023/6/29
 * @desc: 例子： 大雄的妈妈 让 大雄 打扫房间， 大雄 不想做，于是把 哆啦A梦 找来，让其打扫， 哆啦A梦 从口袋里拿出了一个宝贝，名叫 打扫王， 最后 打扫王 把房间打扫好了。
 * 在这个例子里，大雄 是代理对象 p, 哆啦A梦 是处理器对象 h， 打扫王 是真正干活的对象 r
 * 妈妈 -> 大雄p -> 哆啦A梦h -> 打扫王r
 * 使用场景：
 * 1. 可以搭配FactoryBean 生成bean 注入spring容器
 */
public class InvocationHandlerTest {

    public interface Clean {
        void work();
    }

    public static class KingRobot implements Clean {
        public void work() {
            IntStream.range(1, 10).forEach(x -> System.out.printf("KingRobot --》》》打扫房间的任务完成了 %s%%%n", x));
        }
    }

    //哆啦A梦
    public static class DoraemonHandler implements InvocationHandler {
        /**
         * 干活的机器人(即 "打扫王"),真正干活的对象 r
         */
        private final Object target;

        public DoraemonHandler(Object target) {
            this.target = target;
        }

        /**
         * @param proxy 代理对象, 即 大雄
         */
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("DoraemonHandler --》》》proxy 的类型是 " + proxy.getClass().getName());
            System.out.println("DoraemonHandler --》》》被代理的方法的名称为 " + method.getName());
            System.out.println("DoraemonHandler --》》》我是哆啦A梦，脏活累活还是丢给我兜里的宝贝机器人来做吧⤵");
            return method.invoke(target, args);
        }
    }

    //生成代理类的文件
//    static {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
//        new File("com/sun/proxy").mkdirs();
//    }

    public static void main(String[] args) {
//        new InvocationHandlerTest().test1();
        new InvocationHandlerTest().test2();
    }

    //两种方式：1
    public void test1() {
        DoraemonHandler handler = new DoraemonHandler(new KingRobot());
        Clean proxy = (Clean) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Clean.class}, handler);
        System.out.printf("proxy --》》》代理者的类型为 %s%n", proxy.getClass().getName());
        proxy.work();
    }

    //两种方式：2
    public void test2() {
        InvocationHandler handler = new DoraemonHandler(new KingRobot());
        Clean proxy = (Clean) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Clean.class}, handler);
        System.out.printf("proxy --》》》代理者的类型为 %s%n", proxy.getClass().getName());
        proxy.work();
    }

}
