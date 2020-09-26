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

import com.alibaba.fastjson.JSON;
import com.jensen.platform.crm.api.common.queue.SystemLogQueue;
import com.jensen.platform.crm.api.common.utils.ApplicationUtils;
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
 * @ClassName:
 * @Description:(记录操作日志)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@Aspect
@Component
public class AspectLog {

    private static final Logger logger = LoggerFactory.getLogger(AspectLog.class);

    @Resource
    private SystemLogQueue systemLogQueue;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.jensen.platform.crm.api.common.aop.AnnotationLog)")
    public void methodCachePointcut() {}

    @Around("methodCachePointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        long begin = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        AnnotationLog annotationLog = method.getAnnotation(AnnotationLog.class);

        String desc = annotationLog.desc();

        SysOperateLog sysOperateLog = new SysOperateLog();
        sysOperateLog.builder(getUserId(),"sysadmin");
        sysOperateLog.setType(request.getMethod());
        sysOperateLog.setPath(annotationLog.path());
        sysOperateLog.setContent(JSON.toJSONString(proceedingJoinPoint.getArgs()));
        sysOperateLog.setDescription(desc);
        sysOperateLog.setStartTime(new Date());

        logger.info("请求开始");
        logger.info("请求连接 {}",request.getRequestURL().toString());
        logger.info("接口描述 {}",desc);
        logger.info("请求类型 {}",request.getMethod());
        logger.info("请求方法 {}.{}",signature.getDeclaringTypeName(),signature.getName());
        logger.info("请求ip {}",request.getRemoteAddr());
        logger.info("请求入参 {}", JSON.toJSONString(proceedingJoinPoint.getArgs()));
        logger.info("请求token {}",request.getHeader("sso-token"));
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

    private static String getUserId() {
        String userId = "3bbd24ec-fcc3-44b6-bc73-1db85c1a61ed";
        /*UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        if(userInfo != null){
            userId = userInfo.getId();
        }*/
        return userId;
    }
}
