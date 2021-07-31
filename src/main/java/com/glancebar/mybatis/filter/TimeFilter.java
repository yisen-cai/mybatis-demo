package com.glancebar.mybatis.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author YISHEN CAI
 */
public class TimeFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(TimeFilter.class);


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("time filter init...");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = System.currentTimeMillis();
        logger.info("time filter ended...");
        logger.info("time filter last: " + (end - start) + " milliseconds");
    }
}
