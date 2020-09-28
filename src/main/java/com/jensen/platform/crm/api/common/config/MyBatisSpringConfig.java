/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  MyBatisSpringConfig.java
 * @Package com.jensen.platform.crm.api.common.config
 * @author: Jensen
 * @date:   2020/9/28 10:21
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @ClassName:  MyBatisSpringConfig
 * @Description: MyBatisSpring配置
 * @author: Jensen
 * @date:  2020/9/28 10:21
 */
@Configuration
@EnableTransactionManagement
public class MyBatisSpringConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;

    /**
     * @Title:  sqlSessionFactory
     * @Description 在Spring中注册SqlSessionFactory，在这里可以设置一下参数：1.设置分页参数 2.配置MyBatis运行时参数 3.注册xml映射器
     * @Author  Jensen
     * @Date  2020/9/28 10:22
     * @param
     * @Return {@link org.apache.ibatis.session.SqlSessionFactory}
     * @Exception
    */
    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置映射POJO对象包名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.jensen.platform.crm.api.entity");

        // 分页插件
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        //添加插件
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(properties);
        sqlSessionFactoryBean.setPlugins(new Interceptor[] {interceptor});

        // 配置mybatis运行时参数
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 自动将数据库中的下划线转换为驼峰格式
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setDefaultFetchSize(100);
        configuration.setDefaultStatementTimeout(30);

        sqlSessionFactoryBean.setConfiguration(configuration);

        // 在构建SqlSessionFactory时注册xml映射器
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/**/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * @Title:  sqlSessionTemplate
     * @Description 注入sqlSession对象
     * @Author  Jensen
     * @Date  2020/9/28 10:23
     * @param sqlSessionFactory
     * @Return {@link org.mybatis.spring.SqlSessionTemplate}
     * @Exception
    */
    @Bean(value = "sqlSession")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * @Title:  annotationDrivenTransactionManager
     * @Description Spring事务管理器
     * @Author  Jensen
     * @Date  2020/9/28 10:23
     * @param
     * @Return {@link org.springframework.transaction.PlatformTransactionManager}
     * @Exception
    */
    @Bean(value = "transactionManager")
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
