/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  AuthUserController.java
 * @Package com.jensen.platform.crm.api.pojo.vo.auth
 * @author: jensen
 * @date:   2020/10/18 17:16
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.pojo.dto.auth;

import com.jensen.platform.crm.api.common.bean.RequestDTO;

import java.util.Date;
import java.io.Serializable;

/**
* @Description: AuthUserVO类
* @author jensen
* @date 2020/10/18 17:16
*/
public class AuthUserDTO extends RequestDTO implements Serializable {

    /** 属性corpId */
    private String corpId;

    /** 属性loginName */
    private String loginName;

    /** 属性password */
    private String password;

    /** 属性userName */
    private String userName;

    /** 属性userType */
    private String userType;

    /** 属性memberId */
    private String memberId;

    /** 属性mobile */
    private String mobile;

    /** 属性email */
    private String email;

    /** 属性loginTime */
    private Date loginTime;

    /** 属性lastLoginTime */
    private Date lastLoginTime;

    /** 属性loginCount */
    private Long loginCount;

    /** 属性headImgUrl */
    private String headImgUrl;


    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

}