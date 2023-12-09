package com.org.makgol.common.filter;

import org.jetbrains.annotations.Contract;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@WebFilter("/*")
public class XssFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필요한 경우 초기화 로직 추가
    }

    @Override
    public void destroy() {
        // 필요한 경우 종료 로직 추가
    }
}
