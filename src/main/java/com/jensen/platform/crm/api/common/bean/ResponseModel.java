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
package com.jensen.platform.crm.api.common.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jensen.platform.crm.api.common.enums.HttpStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName:
 * @Description:(全局API接口)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "全局API接口")
public class ResponseModel<T> implements Serializable {

    @ApiModelProperty(value = "状态码,成功为200", example = "200")
    private int code;

    @ApiModelProperty(value = "成功标识", example = "true")
    private boolean success;

    @ApiModelProperty(value = "状态码为200时，message为OK， 否则为错误信息", example = "OK")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    @ApiModelProperty(value = "时间戳", example = "1853558969352")
    private long timestamp;

    public ResponseModel(HttpStatus status, T data) {
        this.code = status.code;
        this.message = status.message;
        this.data = data;
    }
}
