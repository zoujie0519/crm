/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  JwtUserDetailsService.java
 * @Package com.jensen.platform.crm.api.common.security
 * @author: Jensen
 * @date:   2020/9/28 10:44
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.security;

import com.jensen.platform.crm.api.entity.auth.AuthUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:  JwtUserDetailsService
 * @Description: JWT用户服务自定义类
 * @author: Jensen
 * @date:  2020/10/17 18:15
 */
@Log4j2
@Service
public class JWTUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        log.info("JwtUserDetailsService：{}", user);
        AuthUser authUser = new AuthUser();
        authUser.setLoginName(user);
        authUser.setPassword("$2a$10$7ha46kngKqaHSKBGQixid.c/rZ.McPyYjnPN/mDJ./TftQ6WPXbty");
        return new JWTUser(authUser);
    }

}
