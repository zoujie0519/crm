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
package com.jensen.platform.crm.api.common.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName:
 * @Description:(描述这个类的作用)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException {

        System.out.println("JwtAuthenticationEntryPoint:"+authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"没有凭证");
    }
}
