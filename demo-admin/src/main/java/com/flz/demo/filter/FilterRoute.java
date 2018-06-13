package com.flz.demo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterRoute implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取当前请求
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //带参数跳转 url地址不会变 是在服务器端进行的转向 sendRedirect是在客户端进行的
        //request.getRequestDispatcher("/test2").forward(request,response);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
