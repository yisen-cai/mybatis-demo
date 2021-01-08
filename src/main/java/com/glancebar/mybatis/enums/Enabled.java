package com.glancebar.mybatis.enums;

public enum Enabled {
    ENABLED(1),
    DISABLED(0);

    private final Integer value;

    Enabled(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
