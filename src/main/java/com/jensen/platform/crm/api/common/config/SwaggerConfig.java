/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  SwaggerConfig.java
 * @Package com.jensen.platform.crm.api.common.config
 * @author: Jensen
 * @date:   2020/9/28 10:24
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.config;

import com.github.xiaoymin.swaggerbootstrapui.model.OrderExtensions;
import com.jensen.platform.crm.api.common.security.JWTTokenUtils;
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
 * @ClassName:  SwaggerConfig
 * @Description: swagger配置
 * @author: Jensen
 * @date:  2020/9/28 10:24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.show}")
    private Boolean enable;

    @Bean(value = "dataApi")
    public Docket dataApi() {

        // 在请求头中添加一个为 Authorization 的属性 Security拦截器JWT认证的时需要
        Parameter parameter = new ParameterBuilder()
                .name(JWTTokenUtils.TOKEN_HEADER)
                .description("token令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .defaultValue(JWTTokenUtils.TOKEN_PREFIX)
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

    /**
     * @Title:  securityContext
     * @Description security上下文
     * @Author  Jensen
     * @Date  2020/9/28 10:26
     * @param
     * @Return {@link springfox.documentation.spi.service.contexts.SecurityContext}
     * @Exception
    */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    /***
     * @Title:  defaultAuth
     * @Description 默认授权配置
     * @Author  Jensen
     * @Date  2020/9/28 10:26
     * @param
     * @Return {@link java.util.List<springfox.documentation.service.SecurityReference>}
     * @Exception
    */
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(new SecurityReference("BearerToken1", authorizationScopes));
    }

    /***
     * @Title:  apiKey
     * @Description 生产APIkey
     * @Author  Jensen
     * @Date  2020/9/28 10:27
     * @param
     * @Return {@link springfox.documentation.service.ApiKey}
     * @Exception
    */
    private ApiKey apiKey() {
        return new ApiKey("BearerToken", "Authorization", "header");
    }

    /**
     * @Title:  apiInfo
     * @Description 生成API信息
     * @Author  Jensen
     * @Date  2020/9/28 10:28
     * @param
     * @Return {@link springfox.documentation.service.ApiInfo}
     * @Exception
    */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("crm-api")
                .description("crm-api")
                .termsOfServiceUrl("http://www.jensen.com:8088/")
                .contact(new Contact("jensen", "www.jensen.com", "zoujie0519@163.com"))
                .version("1.0")
                .build();
    }
}
