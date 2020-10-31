/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  WebSecurityConfig.java
 * @Package com.jensen.platform.crm.api.common.config
 * @author: Jensen
 * @date:   2020/9/28 10:30
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.config;

import com.jensen.platform.crm.api.common.security.JWTAuthenticationEntryPoint;
import com.jensen.platform.crm.api.common.security.JWTAuthenticationFilter;
import com.jensen.platform.crm.api.common.security.JWTAuthorizationFilter;
import com.jensen.platform.crm.api.common.security.JWTUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName:  WebSecurityConfig
 * @Description: WebSecurity配置
 * @author: Jensen
 * @date:  2020/9/28 10:30
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JWTUserDetailsService jwtUserDetailsService;
    
    /**
     * @title:  passwordEncoderBean
     * @description 加密算法生成bean
     * @return org.springframework.security.crypto.password.PasswordEncoder
     * @author  Jensen
     * @date  2020/10/31 15:45
     */
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @title:  configureGlobal
     * @description 使用自定义登录身份认证组件
     * @param auth: 
     * @return void
     * @exception
     * @author  Jensen
     * @date  2020/10/31 15:46
     */
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(userDetailsService());
        //jwtAuthenticationProvider.setPasswordEncoder(new MyPasswordEncoder());
        //auth.authenticationProvider(jwtAuthenticationProvider);
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
    }

    /**
     * @title:  configure
     * @description 解决静态资源被拦截的问题
     * @param web:
     * @return void
     * @exception
     * @author  Jensen
     * @date  2020/10/31 15:49
     */
    public void configure(WebSecurity web) {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/favicon.ico","/error","/doc.html","/webjars/**","/swagger**/**","/v2/**");
    }

    /***
     * @Title:  configure
     * @Description 安全配置
     * @Author  Jensen
     * @Date  2020/9/28 10:31
     * @param http
     * @Return
     * @Exception
    */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 测试用资源，需要验证了的用户才能访问
                .antMatchers("/sys/**").authenticated()
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 统一结果处理
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
