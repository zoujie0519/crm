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
package com.jensen.platform.crm.api.common.enums;

/**
 * @ClassName:
 * @Description:(描述这个类的作用)
 * @author: jensen
 * @date:
 * @Copyright:
 */
public enum SessionCreationPolicy {

    ALWAYS,//总是会新建一个Session。
    NEVER,//不会新建HttpSession，但是如果有Session存在，就会使用它。
    IF_REQUIRED,//如果有要求的话，会新建一个Session。
    STATELESS;//这个是我们用的，不会新建，也不会使用一个HttpSession。

    private SessionCreationPolicy() {
    }
}
