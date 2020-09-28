/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  JwtAuthToken.java
 * @Package com.jensen.platform.crm.api.common.bean
 * @author: Jensen
 * @date:   2020/9/28 10:07
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.bean;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @ClassName:  JwtAuthToken
 * @Description: 自定义令牌对象
 * @author: Jensen
 * @date:  2020/9/28 10:04
 */
public class JwtAuthToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = 1L;

    /** 令牌 */
    private String token;

    /**
     * @Title:  JwtAuthToken
     * @Description 默认构造函数
     * @Author  Jensen
     * @Date  2020/9/28 10:05
     * @param principal
     * @param credentials
     * @Return {@link null}
     * @Exception
    */
    public JwtAuthToken(Object principal, Object credentials){
        super(principal, credentials);
    }

    /**
     * @Title:  JwtAuthToken
     * @Description 带token构造函数
     * @Author  Jensen
     * @Date  2020/9/28 10:05
     * @param principal
     * @param credentials
     * @param token
     * @Return {@link null}
     * @Exception
    */
    public JwtAuthToken(Object principal, Object credentials, String token){
        super(principal, credentials);
        this.token = token;
    }

    /**
     * @Title:  JwtAuthToken
     * @Description 带token，权限构造函数
     * @Author  Jensen
     * @Date  2020/9/28 10:06
     * @param principal
     * @param credentials
     * @param authorities
     * @param token
     * @Return {@link null}
     * @Exception
    */
    public JwtAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String token) {
        super(principal, credentials, authorities);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
