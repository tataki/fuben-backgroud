package com.foo.common.enums;

public enum StatusEnum {
    progressing(1,"筹集中"),
    executing(2,"执行中"),
    end(3,"已结束");
    public final Integer type;
    public final String value;

    StatusEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
