package com.glancebar.mybatis.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YISHEN CAI
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(TimeInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("pre handle..");
        request.setAttribute("startTime", System.currentTimeMillis());
        try {
            logger.info(((HandlerMethod) request).getMethod().getName());
        } catch (ClassCastException e) {
            logger.error(e.getMessage());
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("post handle");
        long startTime = (long) request.getAttribute("startTime");
        logger.info("last " + (System.currentTimeMillis() - startTime) + " milliseconds");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("after completion");
    }
}
