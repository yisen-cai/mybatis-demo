package com.glancebar.mybatis.enums;

/**
 * @author YISHEN CAI
 */
public enum GenderEnum {

    /**
     * 性别
     */
    MALE(0),
    FEMALE(1),
    UNKNOWN(2),
    ;

    private final Integer value;

    GenderEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
