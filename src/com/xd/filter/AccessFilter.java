package com.xd.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by DuTing on 2016/3/19.
 */

public class AccessFilter implements Filter {

    private static final Logger logger = Logger.getLogger(AccessFilter.class);

    // FilterConfig可用于访问Filter的配置信息
    private FilterConfig config;

    public void init(FilterConfig filterConfig) {
        this.config = filterConfig;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获取该Filter的配置参数
        String encoding = config.getInitParameter("encoding");

        // 设置request编码用的字符集
        servletRequest.setCharacterEncoding(encoding);
        // ①
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        // 获取客户请求的页面
        String requestPath = request.getServletPath();

        filterChain.doFilter(servletRequest, servletResponse);
        logger.fatal("doFilter");
    }

    public void destroy() {
        this.config = null;
    }
}
