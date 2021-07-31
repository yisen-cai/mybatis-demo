package com.glancebar.mybatis.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YISHEN CAI
 */
@Aspect
@Component
public class WebAspect {
    private final Logger logger = LoggerFactory.getLogger(WebAspect.class);

    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Content-Length,Authorization,X-Requested-With");
        response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        String method = request.getMethod();
        if (method == "OPTIONS") {
            response.setStatus(200);
        }
        logger.debug("url=" + request.getRequestURL());
        logger.debug("method=" + request.getMethod());
    }
}
