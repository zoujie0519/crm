/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  api
 * @Package com.jensen.platform.crm.api.common.security
 * @author: Jensen
 * @date:   2020/9/29 9:33
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jensen.platform.crm.api.common.bean.Message;
import com.jensen.platform.crm.api.common.enums.HttpStatus;
import com.jensen.platform.crm.api.entity.auth.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName: JwtLoginAuthFilter
 * @Description: 进行用户账号的验证==>认证功能
 * UsernamePasswordAuthenticationFilter：登录认证过滤器，根据用户名密码进行认证r
 * 该拦截器用于获取用户登录的信息，只需创建一个token并调用authenticationManager.authenticate()
 * 让spring-security去进行验证就可以了，不用自己查数据库再对比密码了，这一步交给spring去操作。
 * @Author: Jensen
 * @Date: 2020/9/29 9:33
 * @Version: V1.0
 **/
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    /** AuthenticationManager： 用户认证的管理类，所有的认证请求（比如login）都会通过提交一个token给AuthenticationManager的authenticate()方法来实现。
        当然事情肯定不是它来做，具体校验动作会由AuthenticationManager将请求转发给具体的实现类来做。根据实现反馈的结果再调用具体的Handler来给用户以反馈。*/
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * @title:  attemptAuthentication
     * @description 从输入流中获取到登录的信息，尝试身份验证
     * @param request:
     * @param response:
     * @return org.springframework.security.core.Authentication
     * @exception
     * @author  Jensen
     * @date  2020/10/31 16:20
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        JWTUser loginUser = null;
        try {
            loginUser = new ObjectMapper().readValue(request.getInputStream(), JWTUser.class);
        } catch (IOException e) {
            logger.info("从输入流中获取到登录的信息失败1：", e);
        }

        try {
            if(loginUser == null) {
                AuthUser authUser = new AuthUser();
                authUser.setLoginName(request.getParameter("account"));
                authUser.setPassword(request.getParameter("password"));
                loginUser = new JWTUser(authUser);
            }
        } catch (Exception e) {
            logger.info("从输入流中获取到登录的信息失败2：", e);
            return null;
        }

        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>())
        );
    }

    /**
     * @Title:  successfulAuthentication
     * @Description 登录验证成功后调用，验证成功后将生成Token
     * 与AuthenticationSuccessHandler作用相同
     * @Author  Jensen
     * @Date  2020/9/29 9:50
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @Return
     * @Exception
    */
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象，这里是JwtAuthUser
        JWTUser jwtAuthUser = (JWTUser) authResult.getPrincipal();
        logger.info("JwtAuthUser: {}", jwtAuthUser);

        List<String> roles = new ArrayList<>();
        Collection<? extends GrantedAuthority> authorities = jwtAuthUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            roles.add(authority.getAuthority());
        }
        logger.info("roles: {}", roles);
        // 返回创建成功的token，这里创建的token只是单纯的token
        String token = JWTTokenUtils.createToken(jwtAuthUser.getUsername(), roles, true);
        logger.info("token: {}", token);
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader(JWTTokenUtils.TOKEN_HEADER, JWTTokenUtils.TOKEN_PREFIX + token);
    }

    /**
     * @Title:  unsuccessfulAuthentication
     * @Description 登录验证失败后调用，这里直接Json返回，实际上可以重定向到错误界面等
     * 与AuthenticationFailureHandler作用相同
     * @Author  Jensen
     * @Date  2020/9/29 10:00
     * @param request
     * @param response
     * @param failed
     * @Return
     * @Exception
    */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Message.error(HttpStatus.JWT_EXPIRED, response);
    }
}
