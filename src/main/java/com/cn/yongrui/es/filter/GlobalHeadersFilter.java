package com.cn.yongrui.es.filter;

import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalHeadersFilter implements Filter {

    private final static String TRACE_ID="X-B3-TraceId";

    private void addTraceId(HttpServletResponse response){
        response.addHeader("traceId", MDC.get(TRACE_ID));
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        addTraceId(response);
        filterChain.doFilter(servletRequest,response);
    }
}
