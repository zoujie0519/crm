/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  JwtAuthenticationEntryPoint.java
 * @Package com.jensen.platform.crm.api.common.security
 * @author: Jensen
 * @date:   2020/9/28 10:43
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
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
 * @ClassName:  JwtAuthenticationEntryPoint
 * @Description: TODO(描述这个类的作用)
 * @author: Jensen
 * @date:  2020/9/28 10:44
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
