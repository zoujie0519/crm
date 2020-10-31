/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  JWTAuthorizationFilter.java
 * @Package com.jensen.platform.crm.api.common.security
 * @author: Jensen
 * @date:   2020/9/28 10:44
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName:  JwtAuthorizationTokenFilter
 * @Description: 对所有请求进行过滤
 * @author: Jensen
 * @date:  2020/10/17 18:07
 */
@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * @Title:  doFilterInternal
     * @Description 从request的header部分读取Token
     * @Author  Jensen
     * @Date  2020/10/17 17:39
     * @Param request
     * @Param response
     * @Param chain
     * @Return void
     * @Exception
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JWTTokenUtils.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JWTTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        super.doFilterInternal(request, response, chain);
    }

    /**
     * @Title:  getAuthentication
     * @Description 读取Token信息，创建UsernamePasswordAuthenticationToken对象
     * @Author  Jensen
     * @Date  2020/10/17 17:48
     * @Param tokenHeader
     * @Return org.springframework.security.authentication.UsernamePasswordAuthenticationToken
     * @Exception
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        //解析Token时将“Bearer ”前缀去掉
        String token = tokenHeader.replace(JWTTokenUtils.TOKEN_PREFIX, "");
        String username = JWTTokenUtils.getUsername(token);
        List<String> roles = JWTTokenUtils.getUserRole(token);
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (roles!=null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null, authorities);
        }
        return null;
    }
}
