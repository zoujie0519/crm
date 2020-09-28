/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  XSSFilterConfig.java
 * @Package com.jensen.platform.crm.api.common.config
 * @author: Jensen
 * @date:   2020/9/28 10:30
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.config;

import com.jensen.platform.crm.api.common.filter.XSSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:  XSSFilterConfig
 * @Description: xss过滤拦截器配置文件
 * @author: Jensen
 * @date:  2020/9/28 10:29
 */
@Configuration
public class XSSFilterConfig {

    /**
     * @Title:  xssFilterRegistrationBean
     * @Description xss过滤拦截器
     * @Author  Jensen
     * @Date  2020/9/28 10:29
     * @param
     * @Return {@link org.springframework.boot.web.servlet.FilterRegistrationBean}
     * @Exception
    */
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XSSFilter());
        filterRegistrationBean.setOrder(Integer.MAX_VALUE-1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap();
        //excludes用于配置不需要参数过滤的请求url
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        //isIncludeRichText主要用于设置富文本内容是否需要过滤
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }
}
