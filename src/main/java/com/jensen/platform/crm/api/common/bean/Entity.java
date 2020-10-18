/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  Entity.java
 * @Package com.jensen.platform.crm.api.common.bean
 * @author: Jensen
 * @date:   2020/9/28 10:02
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.bean;

import com.jensen.platform.crm.api.common.constant.Constant;
import com.jensen.platform.crm.api.common.utils.ApplicationUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ClassName:  Entity
 * @Description: 数据库实体基础类
 * @author: Jensen
 * @date:  2020/9/28 10:02
 */
public class Entity {

    /**
     * 记录标识
     */
    @Id
    @Column(name = "Id")
    private String id;

    /**
     * 创建人记录标识
     */
    @Column(name = "CreateId")
    private String createId;

    /**
     * 创建人
     */
    @Column(name = "CreateBy")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "CreateTime")
    private Date createTime;

    /**
     * 更新人记录标识
     */
    @Column(name = "ModifyId")
    private String modifyId;

    /**
     * 更新人
     */
    @Column(name = "ModifyBy")
    private String modifyBy;

    /**
     * 更新时间
     */
    @Column(name = "ModifyTime")
    private Date modifyTime;

    /**
     * 是否删除
     */
    @Column(name = "IsDeleted")
    private Boolean isDeleted;

    /**
     * 获取记录标识
     *
     * @return Id - 记录标识
     */
    public String getId() {
        return id;
    }

    /**
     * 设置记录标识
     *
     * @param id 记录标识
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取创建人记录标识
     *
     * @return CreateId - 创建人记录标识
     */
    public String getCreateId() {
        return createId;
    }

    /**
     * 设置创建人记录标识
     *
     * @param createId 创建人记录标识
     */
    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    /**
     * 获取创建人
     *
     * @return CreateBy - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * 获取创建时间
     *
     * @return CreateTime - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人记录标识
     *
     * @return ModifyId - 更新人记录标识
     */
    public String getModifyId() {
        return modifyId;
    }

    /**
     * 设置更新人记录标识
     *
     * @param modifyId 更新人记录标识
     */
    public void setModifyId(String modifyId) {
        this.modifyId = modifyId == null ? null : modifyId.trim();
    }

    /**
     * 获取更新人
     *
     * @return ModifyBy - 更新人
     */
    public String getModifyBy() {
        return modifyBy;
    }

    /**
     * 设置更新人
     *
     * @param modifyBy 更新人
     */
    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    /**
     * 获取更新时间
     *
     * @return ModifyTime - 更新时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置更新时间
     *
     * @param modifyTime 更新时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取是否删除
     *
     * @return IsDeleted - 是否删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @Title:  builder
     * @Description 构造初始化数据
     * @Param userId
     * @Param userName
     * @Return void
     * @Exception
     * @Author  Jensen
     * @Date  2020/10/18 16:53
     */
    public void builder(String userId, String userName) {
        this.id = ApplicationUtils.randomUUID();
        this.createId = userId;
        this.createBy = userName;
        this.createTime = new Date();
        this.modifyId = userId;
        this.modifyBy = userName;
        this.modifyTime = new Date();
        this.isDeleted = false;
    }

    /**
     * @Title:  builder
     * @Description 构造初始化数据
     * @Return
     * @Exception
     * @Author  Jensen
     * @Date  2020/9/28 10:02
     */
    public void builder() {
        this.id = ApplicationUtils.randomUUID();
        this.createId = Constant.SYSTEM_ADMIN_ID;
        this.createBy = Constant.SYSTEM_ADMIN_NAME;
        this.createTime = new Date();
        this.modifyId = Constant.SYSTEM_ADMIN_ID;
        this.modifyBy = Constant.SYSTEM_ADMIN_NAME;
        this.modifyTime = new Date();
        this.isDeleted = false;
    }

    /**
     * @Title:  toString
     * @Description 重写tosString方法
     * @Author  Jensen
     * @Date  2020/9/28 10:03
     * @param
     * @Return {@link java.lang.String}
     * @Exception
    */
    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
