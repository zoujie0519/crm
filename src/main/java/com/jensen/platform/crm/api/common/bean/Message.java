/*
 * All rights Reserved, Designed By www.jensen.com
 * @title:  Message.java
 * @package com.jensen.platform.crm.api.common.bean
 * @author: Jensen
 * @date:   2020/10/25 10:20
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jensen.platform.crm.api.common.enums.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @className:  Message
 * @description: ResponseModel快捷构建类
 * @author: Jensen
 * @date:  2020/10/25 10:17
 */
public final class Message {

    private Message() {
        throw new UnsupportedOperationException();
    }

    /**
     * @Title:  success
     * @Description 返回成功
     * @param
     * @Return {@link com.jensen.platform.crm.api.common.bean.ResponseModel}
     * @Exception
     * @Author  Jensen
     * @Date  2020/9/28 10:09
    */
    public static ResponseModel<String> success() {
        return new ResponseModel<String>(HttpStatus.OK, null);
    }

    /***
     * @Title:  success
     * @Description 返回成功并携带数据
     * @Author  Jensen
     * @Date  2020/9/28 10:09
     * @param data
     * @Return {@link com.jensen.platform.crm.api.common.bean.ResponseModel<T>}
     * @Exception
    */
    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel<T>(HttpStatus.OK, data);
    }

    /**
     * @title:  success
     * @description 返回成功并携带数据，可定制返回流
     * @param data:
     * @param response:
     * @return void
     * @exception IOException
     * @author  Jensen
     * @date  2020/10/25 10:11
     */
    public static <T> void success(T data, HttpServletResponse response) throws IOException {
        write(HttpStatus.OK, data, response);
    }

    /***
     * @Title:  error
     * @Description 返回错误并携带数据
     * @Author  Jensen
     * @Date  2020/9/28 10:11
     * @param data
     * @Return {@link com.jensen.platform.crm.api.common.bean.ResponseModel<T>}
     * @Exception
    */
    public static <T> ResponseModel<T> error(T data) {
        return new ResponseModel<T>(HttpStatus.SERVICE_ERROR, data);
    }

    /**
     * @Title:  error
     * @Description 返回错误
     * @Author  Jensen
     * @Date  2020/9/28 10:11
     * @param
     * @Return {@link com.jensen.platform.crm.api.common.bean.ResponseModel<T>}
     * @Exception
    */
    public static <T> ResponseModel<T> error() {
        return error(HttpStatus.SERVICE_ERROR);
    }

    /**
     * @title:  error
     * @description 返回指定错误
     * @param status:
     * @return com.jensen.platform.crm.api.common.bean.ResponseModel<T>
     * @exception
     * @author  Jensen
     * @date  2020/10/25 10:08
     */
    public static <T> ResponseModel<T> error(HttpStatus status) {
        return new ResponseModel<T>(status, null);
    }

    /**
     * @title:  error
     * @description 返回指定错误并携带数据
     * @param status:
     * @param data:
     * @return com.jensen.platform.crm.api.common.bean.ResponseModel<T>
     * @exception
     * @author  Jensen
     * @date  2020/10/25 10:12
     */
    public static <T> ResponseModel<T> error(HttpStatus status, T data) {
        return new ResponseModel<T>(status, data);
    }

    /**
     * @title:  error
     * @description 返回指定错误，可定制返回流
     * @param status:
     * @param response:
     * @return void
     * @exception IOException
     * @author  Jensen
     * @date  2020/10/25 10:12
     */
    public static void error(HttpStatus status, HttpServletResponse response) throws IOException {
        write(status, "", response);
    }

    /**
     * @title:  error
     * @description 返回指定错误并携带数据，可定制返回流
     * @param status:
     * @param data:
     * @param response:
     * @return void
     * @exception IOException
     * @author  Jensen
     * @date  2020/10/25 10:12
     */
    public static <T> void error(HttpStatus status, T data, HttpServletResponse response) throws IOException {
        write(status, data, response);
    }

    /**
     * @title:  write
     * @description 定制返回流
     * @param status:
     * @param data:
     * @param response:
     * @return void
     * @exception IOException
     * @author  Jensen
     * @date  2020/10/25 10:13
     */
    private static <T> void write(HttpStatus status, T data, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Writer writer = response.getWriter();
        ObjectWriter objectWriter = mapper.writer();
        objectWriter.writeValue(writer, new ResponseModel<T>(status, data));
    }
}
