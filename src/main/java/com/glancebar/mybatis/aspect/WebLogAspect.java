package com.glancebar.mybatis.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author YISHEN CAI
 */
@Aspect
@Component
public class WebLogAspect {

    private final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);


    @Pointcut(value = "execution(public * com.glancebar.mybatis.controller.*.*(..))")
    public void webLog() {
    }

    @Before(value = "webLog()")
    public void doBefore() {
        logger.info("aspect init .............................");
    }
}
