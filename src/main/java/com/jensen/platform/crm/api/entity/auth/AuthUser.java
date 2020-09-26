package com.jensen.platform.crm.api.entity.auth;

import com.jensen.platform.crm.api.common.bean.Entity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "auth_user")
public class AuthUser extends Entity {
    /**
     * 所属公司ID
     */
    @Column(name = "CorpId")
    private String corpId;

    /**
     * 登录名
     */
    @Column(name = "LoginName")
    private String loginName;

    /**
     *  登录密码
     */
    @Column(name = "Password")
    private String password;

    /**
     * 用户姓名
     */
    @Column(name = "UserName")
    private String userName;

    /**
     * 手机号
     */
    @Column(name = "Mobile")
    private String mobile;

    /**
     * 电子邮箱
     */
    @Column(name = "Email")
    private String email;

    /**
     * 登录时间
     */
    @Column(name = "LoginTime")
    private Date loginTime;

    /**
     * 上次登录时间
     */
    @Column(name = "LastLoginTime")
    private Date lastLoginTime;

    /**
     * 登录次数
     */
    @Column(name = "LoginCount")
    private Long loginCount;

    /**
     * 头像图片地址
     */
    @Column(name = "HeadImgUrl")
    private String headImgUrl;

    /**
     * 父Id
     */
    @Column(name = "ParentId")
    private String parentId;

    /**
     * 路径
     */
    @Column(name = "IdPath")
    private String idPath;

    /**
     * 用户类型
     */
    private String type;

    /**
     * 获取所属公司ID
     *
     * @return CorpId - 所属公司ID
     */
    public String getCorpId() {
        return corpId;
    }

    /**
     * 设置所属公司ID
     *
     * @param corpId 所属公司ID
     */
    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }

    /**
     * 获取登录名
     *
     * @return LoginName - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取 登录密码
     *
     * @return Password -  登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 登录密码
     *
     * @param password  登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取用户姓名
     *
     * @return UserName - 用户姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户姓名
     *
     * @param userName 用户姓名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取手机号
     *
     * @return Mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取电子邮箱
     *
     * @return Email - 电子邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置电子邮箱
     *
     * @param email 电子邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取登录时间
     *
     * @return LoginTime - 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 设置登录时间
     *
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 获取上次登录时间
     *
     * @return LastLoginTime - 上次登录时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置上次登录时间
     *
     * @param lastLoginTime 上次登录时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取登录次数
     *
     * @return LoginCount - 登录次数
     */
    public Long getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登录次数
     *
     * @param loginCount 登录次数
     */
    public void setLoginCount(Long loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * 获取头像图片地址
     *
     * @return HeadImgUrl - 头像图片地址
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * 设置头像图片地址
     *
     * @param headImgUrl 头像图片地址
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl == null ? null : headImgUrl.trim();
    }

    /**
     * 获取父Id
     *
     * @return ParentId - 父Id
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父Id
     *
     * @param parentId 父Id
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * 获取路径
     *
     * @return IdPath - 路径
     */
    public String getIdPath() {
        return idPath;
    }

    /**
     * 设置路径
     *
     * @param idPath 路径
     */
    public void setIdPath(String idPath) {
        this.idPath = idPath == null ? null : idPath.trim();
    }

    /**
     * 获取用户类型
     *
     * @return type - 用户类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置用户类型
     *
     * @param type 用户类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}