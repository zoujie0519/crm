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
package com.jensen.platform.crm.api.common.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jensen.platform.crm.api.common.enums.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @ClassName:
 * @Description:(ResponseModel快捷构建类)
 * @author: jensen
 * @date:
 * @Copyright:
 */
public final class Message {

    private Message() {
        throw new UnsupportedOperationException();
    }

    public static ResponseModel success() {
        return success("success");
    }

    public static <T> ResponseModel<T> success(T data) {
        return new ResponseModel(HttpStatus.OK, data);
    }

    public static <T> void success(T data, HttpServletResponse response) throws IOException {
        write(HttpStatus.OK, data, response);
    }

    public static <T> ResponseModel<T> error(String data) {
        return new ResponseModel(HttpStatus.SERVICE_ERROR, data);
    }

    public static <T> ResponseModel<T> error() {
        return error(HttpStatus.SERVICE_ERROR);
    }

    public static <T> ResponseModel<T> error(HttpStatus status) {
        return new ResponseModel(status, "error");
    }

    public static <T> ResponseModel<T> error(HttpStatus status, T data) {
        return new ResponseModel(status, data);
    }

    public static void error(HttpStatus status, HttpServletResponse response) throws IOException {
        write(status, "", response);
    }

    public static <T> void error(HttpStatus status, T data, HttpServletResponse response) throws IOException {
        write(status, data, response);
    }

    private static <T> void write(HttpStatus status, T data, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        Writer writer = response.getWriter();
        ObjectWriter objectWriter = mapper.writer();
        objectWriter.writeValue(writer, new ResponseModel(status, data));
    }
}
