package com.spring.project.organicfoodshop.util.constant;

import lombok.Getter;

@Getter
public enum TargetSubjectEnum {
    ACCOUNT("tài khoản"),
    PRODUCT("sản phẩm");

    private final String displayName;

    TargetSubjectEnum(String displayName) {
        this.displayName = displayName;
    }
}
