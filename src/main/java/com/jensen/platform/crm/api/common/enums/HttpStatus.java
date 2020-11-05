/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  HttpStatus.java
 * @Package com.jensen.platform.crm.api.common.enums
 * @author: Jensen
 * @date:   2020/9/28 10:33
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.enums;

/**
 * @ClassName:  HttpStatus
 * @Description: HTTP状态枚举
 * @author: Jensen
 * @date:  2020/9/28 10:33
 */
public enum HttpStatus {

    JWT_EXPIRED(1001, "Expire JWT"),

    JWT_SIGNATURE_ERROR(1002, "Signature Error"),

    JWT_MALFORMED(1003, "Malformed JWT"),

    OK(200, "OK"),

    USER_NOT_FOUND(3001, "Username Not Found"),

    BAD_CREDENTIALS(3002, "Bad Credentials"),

    USER_LOCKED(3003, "Account Has Been Disabled/Product Error"),

    USER_ALREADY_EXISTS(3004, "User Already Exists"),

    INCORRECT_ORIGINAL_PASSWORD(3005, "Incorrect Original Password"),

    FORBIDDEN(4001, "Forbidden"),

    UNAUTHORIZED(4002, "Unauthorized"),

    NOT_FOUND(4004, "Resources Not Fount"),

    UNKNOWN_ERROR(5000, "Unknown Error"),

    ARGS_VALIDATE_ERROR(5001, "Arguments Validate Error"),

    INTERNAL_ERROR(5002, "Internal Error"),

    SERVICE_ERROR(6000, "Service return error"),

    PRODUCT_TAG_FOUND(7001, "Product Not Found"),

    REPEAT_SUBMIT_OPERATION_EXCEPTION(8001, "Repeat Submit Operation");

    public int code;

    public String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
