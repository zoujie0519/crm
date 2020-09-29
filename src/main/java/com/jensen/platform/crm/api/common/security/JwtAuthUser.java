/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  api
 * @Package com.jensen.platform.crm.api.common.security
 * @author: Jensen
 * @date:   2020/9/29 9:41
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.security;

import com.jensen.platform.crm.api.entity.auth.AuthUser;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @ClassName: JwtAuthUser
 * @Description: 实现UserDetails，封装用户信息，用于验证身份
 * @Author: Jensen
 * @Date: 2020/9/29 9:41
 * @Version: V1.0
 **/
public class JwtAuthUser implements UserDetails {

    private String id;
    private String userName;
    private String password;
    private List<String> roles;
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * @Title:  JwtAuthUser
     * @Description 通过FXUser来创建JwtAuthUser
     * @Author  Jensen
     * @Date  2020/9/29 9:44
     * @param authUser
     * @Return {@link null}
     * @Exception
    */
    public JwtAuthUser(AuthUser authUser){
        this.id = authUser.getId();
        this.userName = authUser.getUserName();
        this.password = authUser.getPassword();
        this.roles = new ArrayList<>();
        this.roles.add("ADMIN");
    }

    /**
     * description: 鉴权最重要方法，通过此方法来返回用户权限
     *
     * @param
     * @return java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        if (roles!=null) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
