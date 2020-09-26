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

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:(描述这个类的作用)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        System.out.println("JwtUserDetailsService:" + user);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new SecurityUserDetails(user,authorityList);
    }

}
