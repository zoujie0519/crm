/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  SecurityUserDetails.java
 * @Package com.jensen.platform.crm.api.common.security
 * @author: Jensen
 * @date:   2020/9/28 10:45
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.security;

import com.jensen.platform.crm.api.entity.auth.AuthUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

/**
 * @ClassName:
 * @Description:(描述这个类的作用)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SecurityUserDetails extends AuthUser implements UserDetails {

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getUsername() {
        return this.getLoginName();
    }

    public SecurityUserDetails(String userName, Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
        this.setLoginName(userName);
        String encode = new BCryptPasswordEncoder().encode("Aa123456");
        this.setPassword(encode);
        this.setAuthorities(authorities);
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
