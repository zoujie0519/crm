/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  XSSHttpServletRequestWrapper.java
 * @Package com.jensen.platform.crm.api.common.filter
 * @author: Jensen
 * @date:   2020/9/28 10:38
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.filter;

import com.jensen.platform.crm.api.common.utils.XSSFilterUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @ClassName:  XSSHttpServletRequestWrapper
 * @Description: 重写请求参数处理函数
 * @author: Jensen
 * @date:  2020/9/28 10:38
 */ 
public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;

    private boolean isIncludeRichText = false;

    public XSSHttpServletRequestWrapper(HttpServletRequest request, boolean isIncludeRichText) {
        super(request);
        orgRequest = request;
        this.isIncludeRichText = isIncludeRichText;
    }

    /**
     * @Title:  getParameter
     * @Description 覆盖getParameter方法，将参数名和参数值都做xss过滤.
     *              如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     *              getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     * @Author  Jensen
     * @Date  2020/9/28 10:40
     * @param name
     * @Return {@link java.lang.String}
     * @Exception
    */
    @Override
    public String getParameter(String name) {
        if (("content".equals(name) || name.endsWith("WithHtml")) && !isIncludeRichText) {
            return super.getParameter(name);
        }
        name = XSSFilterUtils.clean(name);
        String value = super.getParameter(name);
        if (StringUtils.isNotBlank(value)) {
            value = XSSFilterUtils.clean(value);
        }
        return value;
    }

    /**
     * @Title:  getParameterValues
     * @Description 获取参数和值
     * @Author  Jensen
     * @Date  2020/9/28 10:40
     * @param name
     * @Return {@link java.lang.String[]}
     * @Exception
    */
    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = XSSFilterUtils.clean(arr[i]);
            }
        }
        return arr;
    }


    /**
     * @Title:  getHeader
     * @Description 覆盖getHeader方法，将参数名和参数值都做xss过滤。
     *              如果需要获得原始的值，则通过super.getHeaders(name)来获取
     *              getHeaderNames 也可能需要覆盖
     * @Author  Jensen
     * @Date  2020/9/28 10:39
     * @param name
     * @Return {@link java.lang.String}
     * @Exception
    */
    @Override
    public String getHeader(String name) {
        name = XSSFilterUtils.clean(name);
        String value = super.getHeader(name);
        if (StringUtils.isNotBlank(value)) {
            value = XSSFilterUtils.clean(value);
        }
        return value;
    }

    /**
     * @Title:  getOrgRequest
     * @Description 获取最原始的request
     * @Author  Jensen
     * @Date  2020/9/28 10:39
     * @param
     * @Return {@link javax.servlet.http.HttpServletRequest}
     * @Exception
    */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * @Title:  getOrgRequest
     * @Description 获取最原始的request的静态方法
     * @Author  Jensen
     * @Date  2020/9/28 10:39
     * @param request
     * @Return {@link javax.servlet.http.HttpServletRequest}
     * @Exception
    */
    public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
        if (request instanceof XSSHttpServletRequestWrapper) {
            return ((XSSHttpServletRequestWrapper) request).getOrgRequest();
        }
        return request;
    }
}
