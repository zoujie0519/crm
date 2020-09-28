/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  SecurityConstant.java
 * @Package com.jensen.platform.crm.api.common.constant
 * @author: Jensen
 * @date:   2020/9/28 10:32
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.constant;

/**
 * @ClassName:  SecurityConstant
 * @Description: 安全相关的常量
 * @author: Jensen
 * @date:  2020/9/28 10:32
 */
public class SecurityConstant extends Constant {
    public static final String BAD_TOKEN = "BAD_TOKEN";
    public static final String SUBJECT = "POSEIDON_CLAIM";
    public static final String ISSUER = "security";
    public static final String RANDOM = "RANDOM";
    public static final String LOGOUT = "/logout" ;
    public static final String LOGIN_URL = "/sign_in";
    public static final String TOKEN_SPLIT = ",";
}
