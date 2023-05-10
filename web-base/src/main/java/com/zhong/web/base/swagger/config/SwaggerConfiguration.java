package com.zhong.web.base.swagger.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelSpecification;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.List;

/**
 * Swagger配置
 *
 * @author ashui
 * @date 2018/4/30
 */
@Configuration
@EnableOpenApi
@ConditionalOnMissingBean(Docket.class)
public class SwaggerConfiguration extends BaseSwaggerConfig {

    @Override
    protected void buildParameter(List<RequestParameter> pars) {
        RequestParameterBuilder tokenPar = new RequestParameterBuilder();
//        tokenPar.name("Authorization").description("Bearer ").required(false).contentModel(new ModelSpecification()).build();
        pars.add(tokenPar.build());
    }

    @Override
    protected String getTitle() {
        return "发现杭州项目文档";
    }

    @Override
    protected String getPkgName() {
        return "com.zhong.controller";
    }

    @Bean
    public Docket documentBean() {
        return createRestApi();
    }

}