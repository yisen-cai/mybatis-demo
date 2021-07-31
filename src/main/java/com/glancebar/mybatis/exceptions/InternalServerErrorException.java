package com.glancebar.mybatis.exceptions;

/**
 * @author YISHEN CAI
 */
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
