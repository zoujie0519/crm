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
 * @ClassName:
 * @Description:(全局异常处理)
 * @author: jensen
 * @date:
 * @Copyright:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseModel handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return Message.error(HttpStatus.NOT_FOUND);
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseModel handleBusinessException(BusinessException e){
        logger.info("业务异常：{}", e.getMessage());
        return Message.error(HttpStatus.NOT_FOUND);
    }

    /**
     * 处理所有接口数据验证异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    ResponseModel handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        logger.info("参数错误：{}", e.getMessage());
        // 400901, e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        return Message.error(HttpStatus.NOT_FOUND);
    }
}
