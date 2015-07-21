package com.tyrael.laundry.web.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(TestFilter.class);

    @Override
    public void destroy() {
        LOG.debug("destroy called on TestFilter!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        LOG.debug("doFilter called on TestFilter!");
        String query = "SELECT {[Measures].[Amount Paid], [Measures].[Amount Due]} ON columns, NON EMPTY {[Date Received].[All Dates].[2000]:[Date Received].[All Dates].[2027]} ON ROWS FROM [JobOrder]";
        request.setAttribute("qqq", query);
        request.setAttribute("msg", "Hello warld");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        LOG.debug("init TestFilter!");
    }

}
