/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  AnnotationLog.java
 * @Package com.jensen.platform.crm.api.common.aop
 * @author: Jensen
 * @date:   2020/9/26 16:42
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.aop;

import java.lang.annotation.*;

/**
 * @ClassName:  AnnotationLog
 * @Description: TODO(自定义日志注解)
 * @author: Jensen
 * @date:  2020/9/26 16:43
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
