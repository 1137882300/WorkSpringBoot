package com.zhong.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 环境上下文
 */
@Configuration
public class AppContext {

    public static boolean isProd;
    public static boolean isPre;
    public static boolean isLocalOrDev;
    public static boolean isLocal;
    @Value("${spring.profiles.active:local}")
    private String active;

    @PostConstruct
    public void initConstants() {
        isProd = "prod".equals(this.active) || "pre".equals(this.active);
        isPre = "pre".equals(this.active);
        isLocalOrDev = !isProd;
        isLocal = "local".equals(active);
    }

}
