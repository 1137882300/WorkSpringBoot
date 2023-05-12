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
 * @link <a><a href="https://doc.xiaominfo.com/guide/#%E7%AE%80%E4%BB%8B">...</a></a>
 * 地址：<a href="http://localhost:9090/doc.html">...</a>
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
                .globalRequestParameters(pars)
                .groupName("group-name")
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(getTitle())
                .description("简介")
                .termsOfServiceUrl("服务Url")
                .contact(new Contact("作者", "url", "email"))
                .version("版本")
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