package com.zhong.web.base.config.valid;

import org.springframework.boot.validation.MessageInterpolatorFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.HashMap;

/**
 *
 */
@Configuration
public class ValidationConfig {

    @Bean
    @Primary
    public static LocalValidatorFactoryBean defaultValidator2() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();

        //配置消息插值器：用于处理验证错误消息中的占位符，并生成最终的消息文本。
        MessageInterpolatorFactory interpolatorFactory = new MessageInterpolatorFactory();
        factoryBean.setMessageInterpolator(interpolatorFactory.getObject());

        //这个属性表明在遇到第一个验证错误时立即停止验证。
        factoryBean.setValidationPropertyMap(new HashMap<String, String>() {{
            put("hibernate.validator.fail_fast", "true");
        }});

        return factoryBean;
    }
}
