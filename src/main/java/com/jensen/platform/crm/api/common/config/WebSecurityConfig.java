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

import com.jensen.platform.crm.api.common.security.JwtLoginAuthFilter;
import com.jensen.platform.crm.api.common.security.UnAuthorizedEntryPoint;
import com.jensen.platform.crm.api.common.security.JwtAuthorizationTokenFilter;
import com.jensen.platform.crm.api.common.security.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
    JwtUserDetailsService jwtUserDetailsService;

    /**
     * @Title:  configureGlobal
     * @Description  加载userDetailsService，用于从数据库中取用户信息
     * @Author  Jensen
     * @Date  2020/9/28 10:31
     * @param auth
     * @Return
     * @Exception
    */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoderBean());
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
        http.cors()
                .and()
                // 关闭csrf
                .csrf().disable()
                // 关闭session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic().authenticationEntryPoint(new UnAuthorizedEntryPoint())
                .and()
                .authorizeRequests()
                // 需要角色为ADMIN才能删除该资源
                //.antMatchers(HttpMethod.DELETE,"/tasks/**").hasAnyRole("ADMIN")
                // 测试用资源，需要验证了的用户才能访问
                //.antMatchers("/tasks/**").authenticated()
                // 其他都放行了
                .anyRequest().permitAll()
                .and()
                .formLogin()
                //.loginPage("/login")
                .defaultSuccessUrl("/doc.html").permitAll()
                .permitAll()
                //.successHandler(new Fx)
                .and()
                .logout()//默认注销行为为logout
                //.logoutSuccessHandler(new FxLogoutSuccessHandler())
                .and()
                // 添加到过滤链中
                // 先是UsernamePasswordAuthenticationFilter用于login校验
                .addFilter(new JwtLoginAuthFilter(authenticationManager()))
                // 再通过OncePerRequestFilter，对其他请求过滤
                .addFilter(new JwtAuthorizationTokenFilter(authenticationManager()));
    }

    /***
     * @Title:  passwordEncoderBean
     * @Description 加密器
     * @Author  Jensen
     * @Date  2020/9/29 9:29
     * @param
     * @Return {@link org.springframework.security.crypto.password.PasswordEncoder}
     * @Exception
    */
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
