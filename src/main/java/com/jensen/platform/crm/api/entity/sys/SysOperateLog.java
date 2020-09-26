package com.jensen.platform.crm.api.entity.sys;

import com.jensen.platform.crm.api.common.bean.Entity;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_operate_log")
public class SysOperateLog extends Entity {
    /**
     * 操作类型
     */
    @Column(name = "Type")
    private String type;

    /**
     * 操作路径
     */
    @Column(name = "Path")
    private String path;

    @Column(name = "Description")
    private String description;

    /**
     * 操作开始时间
     */
    @Column(name = "StartTime")
    private Date startTime;

    /**
     * 操作结束时间
     */
    @Column(name = "EndTime")
    private Date endTime;

    /**
     * 操作耗时
     */
    @Column(name = "Time")
    private Long time;

    /**
     * 操作内容
     */
    @Column(name = "Content")
    private String content;

    /**
     * 获取操作类型
     *
     * @return Type - 操作类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置操作类型
     *
     * @param type 操作类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取操作路径
     *
     * @return Path - 操作路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置操作路径
     *
     * @param path 操作路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取操作开始时间
     *
     * @return StartTime - 操作开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置操作开始时间
     *
     * @param startTime 操作开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取操作结束时间
     *
     * @return EndTime - 操作结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置操作结束时间
     *
     * @param endTime 操作结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取操作耗时
     *
     * @return Time - 操作耗时
     */
    public Long getTime() {
        return time;
    }

    /**
     * 设置操作耗时
     *
     * @param time 操作耗时
     */
    public void setTime(Long time) {
        this.time = time;
    }

    /**
     * 获取操作内容
     *
     * @return Content - 操作内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置操作内容
     *
     * @param content 操作内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}