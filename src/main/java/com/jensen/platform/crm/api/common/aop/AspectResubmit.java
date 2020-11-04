/*
 * All rights Reserved, Designed By www.jensen.com
 * @title: ResubmitDataAspect
 * @projectName api
 * @author Jensen
 * @date 2020/11/1 9:48
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.aop;

import com.alibaba.fastjson.JSONObject;
import com.jensen.platform.crm.api.common.bean.Message;
import com.jensen.platform.crm.api.common.bean.RequestDTO;
import com.jensen.platform.crm.api.common.enums.HttpStatus;
import com.jensen.platform.crm.api.common.lock.ResubmitLock;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @ClassName: AspectResubmit
 * @Description: 数据重复提交校验
 * @author: Jensen
 * @date: 2020/11/1 9:48
 */
@Aspect
@Component
public class AspectResubmit {

    private final static Object PRESENT = new Object();

    @Around("@annotation(com.jensen.platform.crm.api.common.aop.AnnotationResubmit)")
    public Object handleResubmit(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取注解信息
        AnnotationResubmit annotation = method.getAnnotation(AnnotationResubmit.class);
        int delaySeconds = annotation.delaySeconds();
        Object[] pointArgs = joinPoint.getArgs();

        if(pointArgs == null || pointArgs.length == 0) {
            return joinPoint.proceed();
        }

        String key = "";
        //获取第一个参数
        Object firstParam = pointArgs[0];
        if (firstParam instanceof RequestDTO) {
            //解析参数
            JSONObject data = JSONObject.parseObject(firstParam.toString());
            if (data != null) {
                StringBuffer sb = new StringBuffer();
                data.forEach((k, v) -> {
                    sb.append(v);
                });
                //生成加密参数 使用了content_MD5的加密方式
                key = ResubmitLock.handleKey(sb.toString());
            }
        }
        //执行锁
        boolean lock = false;
        try {
            //设置解锁key
            lock = ResubmitLock.getInstance().lock(key, PRESENT);
            if (lock) {
                //放行
                return joinPoint.proceed();
            } else {
                //响应重复提交异常
                return Message.error(HttpStatus.REPEAT_SUBMIT_OPERATION_EXCEPTION);
            }
        } finally {
            //设置解锁key和解锁时间
            ResubmitLock.getInstance().unLock(lock, key, delaySeconds);
        }
    }
}
