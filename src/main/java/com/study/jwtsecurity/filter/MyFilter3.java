package com.study.jwtsecurity.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter3 implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터 3");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
