package com.spring.project.organicfoodshop.util.constant;

import lombok.Getter;

@Getter
public enum ModuleEnum {
    CUSTOMER("Khách hàng"),
    EMPLOYEE("Nhân viên"),
    ACCOUNT("tài khoản"),
    PRODUCT("sản phẩm"),
    CART("giỏ hàng"),
    CART_ITEM("sản phẩm trong giỏ hàng"),
    CATEGORY("danh mục"),
    ORDER("đơn hàng"),
    VOUCHER("mã giảm giá");

    private final String name;

    ModuleEnum(String name) {
        this.name = name;
    }
}
