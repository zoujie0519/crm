/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  AspectLog.java
 * @Package com.jensen.platform.crm.api.common.aop
 * @author: Jensen
 * @date:   2020/9/26 16:44
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.aop;

import com.alibaba.fastjson.JSON;
import com.jensen.platform.crm.api.common.queue.SystemLogQueue;
import com.jensen.platform.crm.api.entity.sys.SysOperateLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @ClassName:  AspectLog
 * @Description: 记录操作日志
 * @author: Jensen
 * @date:  2020/9/26 16:44
 */
@Aspect
@Component
public class AspectLog {

    private static final Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Resource
    private SystemLogQueue systemLogQueue;

    /**
     * @Title:  methodCachePointcut
     * @Description 定义切点
     * @Return void
     * @Exception
     * @Author  Jensen
     * @Date  2020/10/18 17:22
     */
    @Pointcut("@annotation(com.jensen.platform.crm.api.common.aop.AnnotationLog)")
    public void methodCachePointcut() {}

    /**
     * @Title:  around
     * @Description 环绕切面
     * @Param proceedingJoinPoint
     * @Return java.lang.Object
     * @Exception
     * @Author  Jensen
     * @Date  2020/10/18 17:23
     */
    @Around("methodCachePointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        long begin = System.currentTimeMillis();

        String requestUrl = "";
        String requestMethod = "";
        String requestAddr = "";
        String requestToken = "";
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            requestUrl = request.getRequestURL().toString();
            requestMethod = request.getMethod();
            requestAddr = request.getRemoteAddr();
            requestToken = request.getHeader("sso-token");
        } catch (Exception e) {
            logger.warn("获取http请求中的属性出错", e);
        }

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        AnnotationLog annotationLog = method.getAnnotation(AnnotationLog.class);

        String desc = annotationLog.desc();

        SysOperateLog sysOperateLog = new SysOperateLog();
        sysOperateLog.builder();
        sysOperateLog.setType(requestMethod);
        sysOperateLog.setPath(annotationLog.path());
        sysOperateLog.setContent(JSON.toJSONString(proceedingJoinPoint.getArgs()));
        sysOperateLog.setDescription(desc);
        sysOperateLog.setStartTime(new Date());

        logger.info("请求开始");
        logger.info("请求连接 {}", requestUrl);
        logger.info("接口描述 {}", desc);
        logger.info("请求类型 {}", requestMethod);
        logger.info("请求方法 {}.{}",signature.getDeclaringTypeName(),signature.getName());
        logger.info("请求ip {}", requestAddr);
        logger.info("请求入参 {}", JSON.toJSONString(proceedingJoinPoint.getArgs()));
        logger.info("请求token {}", requestToken);
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        long end = System.currentTimeMillis();
        logger.info("请求耗时 {}",end-begin);
        logger.info("请求返回 {}",JSON.toJSON(result));
        logger.info("请求结束");

        sysOperateLog.setEndTime(new Date());
        sysOperateLog.setTime(end-begin);
        systemLogQueue.add(sysOperateLog);
        return result;
    }

    /**
     * @Title:  getUserId
     * @Description 获取当前用户Id
     * @Author  Jensen
     * @Date  2020/9/26 16:52
     * @param
     * @Return {@link java.lang.String}
     * @Exception
    */
    private static String getUserId() {
        String userId = "3bbd24ec-fcc3-44b6-bc73-1db85c1a61ed";
        /*UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        if(userInfo != null){
            userId = userInfo.getId();
        }*/
        return userId;
    }
}
