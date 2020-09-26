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
package com.jensen.platform.crm.api.common.aop;

import java.lang.annotation.*;

/**
 * @ClassName:
 * @Description:(自定义日志注解)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationLog {

    /**
     * 内容操作
     * @return
     */
    String desc() default "没有描述";

    /**
     * 请求路径
     * @return
     */
    String path() default "/";

    /**
     * 是否显示
     * @return
     */
    boolean view() default true;
}
