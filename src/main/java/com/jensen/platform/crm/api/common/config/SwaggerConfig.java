/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:
 * @Package
 * @Description: todo
 * @author: jensen
 * @date:
 * @version V1.0
 * @Copyright:
 */
package com.jensen.platform.crm.api.common.config;

import com.github.xiaoymin.swaggerbootstrapui.model.OrderExtensions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName:
 * @Description:(描述这个类的作用)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.show}")
    private Boolean enable;

    @Bean(value = "authApi")
    public Docket authApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .groupName("test-auth")
                .apiInfo(apiInfo())
                .select()
                //.apis(withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.basePackage("com.jensen.platform.crm.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean(value = "dataApi")
    public Docket dataApi() {

        //在请求头中添加一个为 Authorization 的属性 shiro拦截器 认证的时候会用到
        Parameter parameter = new ParameterBuilder()
                .name("token")
                .description("token令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .groupName("test-data")
                .apiInfo(apiInfo())
                .globalOperationParameters(Collections.singletonList(parameter))
                .select()
                //.apis(withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.basePackage("com.jensen.platform.crm.api.controller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(Lists.newArrayList(new OrderExtensions(1)))
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("crm-api")
                .description("crm-api")
                .termsOfServiceUrl("http://www.jensen.com:8088/")
                .contact(new Contact("jensen", "www.jensen.com", "zoujie0519@163.com"))
                .version("1.0")
                .build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken1", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }
}
