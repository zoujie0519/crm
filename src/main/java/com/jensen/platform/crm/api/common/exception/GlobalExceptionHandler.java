/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  GlobalExceptionHandler.java
 * @Package com.jensen.platform.crm.api.common.exception
 * @author: Jensen
 * @date:   2020/9/28 10:36
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.exception;

import com.jensen.platform.crm.api.common.bean.Message;
import com.jensen.platform.crm.api.common.bean.ResponseModel;
import com.jensen.platform.crm.api.common.enums.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName:  GlobalExceptionHandler
 * @Description: 全局异常处理
 * @author: Jensen
 * @date:  2020/9/28 10:36
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @Title:  handleException
     * @Description 处理所有不可知的异常
     * @Author  Jensen
     * @Date  2020/9/28 10:36
     * @param e
     * @Return {@link com.jensen.platform.crm.api.common.bean.ResponseModel}
     * @Exception
    */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseModel handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Message.error(HttpStatus.NOT_FOUND);
    }

    /**
     * @Title:  handleBusinessException
     * @Description 处理所有业务异常
     * @Author  Jensen
     * @Date  2020/9/28 10:36
     * @param e
     * @Return {@link com.jensen.platform.crm.api.common.bean.ResponseModel}
     * @Exception
    */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseModel handleBusinessException(BusinessException e){
        logger.info("业务异常：{}", e.getMessage());
        return Message.error(HttpStatus.NOT_FOUND);
    }

    /**
     * @Title:  handleMethodArgumentNotValidException
     * @Description 处理所有接口数据验证异常
     * @Author  Jensen
     * @Date  2020/9/28 10:37
     * @param e
     * @Return {@link com.jensen.platform.crm.api.common.bean.ResponseModel}
     * @Exception
    */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResponseModel handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.info("参数错误：{}", e.getMessage());
        // 400901, e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        return Message.error(HttpStatus.NOT_FOUND);
    }
}
