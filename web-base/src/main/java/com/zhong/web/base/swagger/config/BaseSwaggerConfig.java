package com.zhong.web.base.swagger.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger基类配置
 *
 * @see <a>https://doc.xiaominfo.com/guide/#%E7%AE%80%E4%BB%8B</a>
 */
public abstract class BaseSwaggerConfig {

    /**
     * @desc 创建稳定bean
     * @author ashui
     * @date 2019/3/5
     */
    protected Docket createRestApi() {
        List<RequestParameter> pars = new ArrayList<>();
//        buildParameter(pars);

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(getPkgName()))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(getTitle())
                .description("编程喵是一个 Spring Boot+Vue 的前后端分离项目")
                .termsOfServiceUrl("termsOfServiceUrl")
                .contact(new Contact("沉默王二", "https://codingmore.top","www.qing_gee@163.com"))
                .version("v1.0")
                .build();
    }




    /**
     * @desc 构建全局参数
     * @author ashui
     * @date 2019/3/5
     */
    protected void buildParameter(List<RequestParameter> pars) {

    }

    /**
     * @desc 文档标题
     * @author ashui
     * @date 2019/3/5
     */
    protected String getTitle() {
        return "发现杭州 app-api 文档";
    }

    /**
     * @desc 文档包名
     * @author ashui
     * @date 2019/3/5
     */
    protected abstract String getPkgName();

}