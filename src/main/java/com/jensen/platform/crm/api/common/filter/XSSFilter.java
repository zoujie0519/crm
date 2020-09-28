/*
 * All rights Reserved, Designed By www.jensen.com
 * @Title:  XSSFilter.java
 * @Package com.jensen.platform.crm.api.common.filter
 * @author: Jensen
 * @date:   2020/9/28 10:37
 * @version V1.0
 * @Copyright: 2020 www.jensen.com Inc. All rights reserved.
 * 注意：本内容仅限于深圳杰森科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.jensen.platform.crm.api.common.filter;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName:  XSSFilter
 * @Description: (拦截防止xss注入 通过Jsoup过滤请求参数内的特定字符)
 * @author: Jensen
 * @date:  2020/9/28 10:37
 */
public class XSSFilter implements Filter {

    /** 是否过滤富文本内容 */
    private static boolean IS_INCLUDE_RICH_TEXT = true;

    public List<String> excludes = new ArrayList<>();

    /***
     * @Title:  doFilter
     * @Description 执行逻辑
     * @Author  Jensen
     * @Date  2020/9/28 10:38
     * @param request
     * @param response
     * @param filterChain
     * @Return
     * @Exception
    */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (handleExcludeURL(req, resp)) {
            filterChain.doFilter(request, response);
            return;
        }
        XSSHttpServletRequestWrapper xssRequest = new XSSHttpServletRequestWrapper((HttpServletRequest) request, IS_INCLUDE_RICH_TEXT);
        filterChain.doFilter(xssRequest, response);
    }

    private boolean handleExcludeURL(HttpServletRequest request, HttpServletResponse response) {
        if (excludes == null || excludes.isEmpty()) {
            return false;
        }
        String url = request.getServletPath();
        for (String pattern : excludes) {
            Pattern p = Pattern.compile("^" + pattern);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }

    /***
     * @Title:  init
     * @Description 初始化
     * @Author  Jensen
     * @Date  2020/9/28 10:37
     * @param filterConfig
     * @Return
     * @Exception
    */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
        if (StringUtils.isNotBlank(isIncludeRichText)) {
            IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
        }
        String temp = filterConfig.getInitParameter("excludes");
        if (temp != null) {
            String[] url = temp.split(",");
            for (int i = 0; url != null && i < url.length; i++) {
                excludes.add(url[i]);
            }
        }
    }

    /***
     * @Title:  destroy
     * @Description 销毁
     * @Author  Jensen
     * @Date  2020/9/28 10:38
     * @param
     * @Return
     * @Exception
    */
    @Override
    public void destroy() {
    }

}
