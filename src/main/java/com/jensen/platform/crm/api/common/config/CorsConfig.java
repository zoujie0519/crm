/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  CorsConfig.java
 * @Package com.jensen.platform.crm.api.common.config
 * @author: Jensen
 * @date:   2020/9/28 10:19
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName:  CorsConfig
 * @Description: 跨域配置
 * @author: Jensen
 * @date:  2020/9/28 10:19
 */ 
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /***
     * @Title:  addCorsMappings
     * @Description 添加跨域映射
     * @Author  Jensen
     * @Date  2020/9/28 10:20
     * @param registry
     * @Return
     * @Exception
    */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")    // 允许跨域访问的路径
                .allowedOrigins("*")    // 允许跨域访问的源
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")    // 允许请求方法
                .maxAge(168000)    // 预检间隔时间
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(true);    // 是否发送cookie
    }
}
