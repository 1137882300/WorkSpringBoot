package com.zhong.configure;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertyResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: juzi
 * @date: 2023/7/11
 * @desc:
 */
public class ReflectTest {

    /**
     * yml 配置
     * <p>
     * chatbot-api:
     *   launchList: group01,group02 # 启动几组，就配置几组
     *   group01:
     *     groupName: ChatGPT AI 问答助手
     *     groupId: 28885518425541
     *     cookie:
     *     openAiKey:
     *     cronExpression: 0/30 * * * * ?
     *     silenced: false
     *
     */

    public static class test implements EnvironmentAware {
        private final Map<String, Map<String, Object>> taskGroupMap = new HashMap<>();

        @Override
        public void setEnvironment(Environment environment) {
            String prefix = "chatbot-api.";
            String launchListStr = environment.getProperty(prefix + "launchList");
            if (StringUtils.isEmpty(launchListStr)) return;
            for (String groupKey : launchListStr.split(",")) {
                Map<String, Object> taskGroupProps = PropertyUtil.handle(environment, prefix + groupKey, Map.class);
                taskGroupMap.put(groupKey, taskGroupProps);
            }
        }
    }


    //工具类
    public static class PropertyUtil {
        private static int springBootVersion = 1;

        static {
            try {
                Class.forName("org.springframework.boot.bind.RelaxedPropertyResolver");
            } catch (ClassNotFoundException e) {
                springBootVersion = 2;
            }
        }

        /**
         * Spring Boot 1.x is compatible with Spring Boot 2.x by Using Java Reflect.
         */
        @SuppressWarnings("unchecked")
        public static <T> T handle(final Environment environment, final String prefix, final Class<T> targetClass) {
            switch (springBootVersion) {
                case 1:
                    return (T) v1(environment, prefix);
                default:
                    return (T) v2(environment, prefix, targetClass);
            }
        }

        private static Object v1(final Environment environment, final String prefix) {
            try {
                Class<?> resolverClass = Class.forName("org.springframework.boot.bind.RelaxedPropertyResolver");
                Constructor<?> resolverConstructor = resolverClass.getDeclaredConstructor(PropertyResolver.class);
                Method getSubPropertiesMethod = resolverClass.getDeclaredMethod("getSubProperties", String.class);
                Object resolverObject = resolverConstructor.newInstance(environment);
                String prefixParam = prefix.endsWith(".") ? prefix : prefix + ".";
                return getSubPropertiesMethod.invoke(resolverObject, prefixParam);
            } catch (final ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
                           | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }

        /**
         * @description 从Spring Boot的环境变量中获取特定前缀下的配置属性，并将其绑定到给定的目标类上。
         */
        private static Object v2(final Environment environment, final String prefix, final Class<?> targetClass) {
            try {
                //加载这个类
                Class<?> binderClass = Class.forName("org.springframework.boot.context.properties.bind.Binder");
                //获取Binder类的get方法
                Method getMethod = binderClass.getDeclaredMethod("get", Environment.class);
                //获取Binder类的bind、方法
                Method bindMethod = binderClass.getDeclaredMethod("bind", String.class, Class.class);
                //调用get方法，获取Binder对象
                Object binderObject = getMethod.invoke(null, environment);
                //前缀处理
                String prefixParam = prefix.endsWith(".") ? prefix.substring(0, prefix.length() - 1) : prefix;
                //调用bind方法，将配置属性绑定到目标类上，返回绑定结果对象
                Object bindResultObject = bindMethod.invoke(binderObject, prefixParam, targetClass);
                //获取绑定结果对象的get方法
                Method resultGetMethod = bindResultObject.getClass().getDeclaredMethod("get");
                //调用get方法，获取最终的绑定结果并返回
                return resultGetMethod.invoke(bindResultObject);
                //返回结果：
                //groupName: ChatGPT AI 问答助手
                //groupId: 28885518425541
                //cookie:
                //openAiKey:
                //cronExpression: 0/30 * * * * ?
                //silenced: false
            } catch (final ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
                           | IllegalArgumentException | InvocationTargetException ex) {
                throw new RuntimeException(ex.getMessage(), ex);
            }
        }
    }


}
