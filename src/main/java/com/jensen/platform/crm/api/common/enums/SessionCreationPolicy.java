/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  SessionCreationPolicy.java
 * @Package com.jensen.platform.crm.api.common.enums
 * @author: Jensen
 * @date:   2020/9/28 10:33
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.enums;

/**
 * @ClassName:  SessionCreationPolicy
 * @Description: Session枚举
 * @author: Jensen
 * @date:  2020/9/28 10:34
 */
public enum SessionCreationPolicy {

    ALWAYS,//总是会新建一个Session。
    NEVER,//不会新建HttpSession，但是如果有Session存在，就会使用它。
    IF_REQUIRED,//如果有要求的话，会新建一个Session。
    STATELESS;//这个是我们用的，不会新建，也不会使用一个HttpSession。

    private SessionCreationPolicy() {
    }
}
