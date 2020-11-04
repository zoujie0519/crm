/*
 * All rights Reserved, Designed By www.jensen.com
 * @title: AnnotationResubmit
 * @projectName api
 * @author Jensen
 * @date 2020/11/1 9:30
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.aop;

import java.lang.annotation.*;

/**
 * @ClassName: AnnotationResubmit
 * @Description: 幂等自定义注解
 * @author: Jensen
 * @date: 2020/11/1 9:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnnotationResubmit {

    /**
     * @title:  delaySeconds
     * @description 延时时间 在延时多久后可以再次提交
     * @return int Time unit is one second
     * @exception
     * @author  Jensen
     * @date  2020/11/1 9:32
     */
    int delaySeconds() default 20;
}
