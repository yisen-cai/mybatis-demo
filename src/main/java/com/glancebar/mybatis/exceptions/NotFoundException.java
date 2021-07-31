package com.glancebar.mybatis.exceptions;

/**
 * @author YISHEN CAI
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
