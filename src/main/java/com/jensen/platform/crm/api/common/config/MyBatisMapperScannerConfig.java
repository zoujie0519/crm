/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  MyBatisMapperScannerConfig.java
 * @Package com.jensen.platform.crm.api.common.config
 * @author: Jensen
 * @date:   2020/9/28 10:21
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @ClassName:  MyBatisMapperScannerConfig
 * @Description: MyBatisMapper扫描配置
 * @author: Jensen
 * @date:  2020/9/28 10:21
 */
@Configuration
@AutoConfigureAfter(MyBatisSpringConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        // 设置sqlSessionFactory名
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        // 设置接口映射器基础包名
        mapperScannerConfigurer.setBasePackage("com.jensen.platform.crm.api.mapper");
        Properties properties = new Properties();
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }
}
