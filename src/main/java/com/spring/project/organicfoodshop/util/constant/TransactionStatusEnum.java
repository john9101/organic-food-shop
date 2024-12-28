package com.spring.project.organicfoodshop.util.constant;

public enum TransactionStatusEnum {
    SUCCESSFUL("Thành công"),
    INCOMPLETE("Chưa hoàn tất"),
    FAILED("Thất bại");

    final String value;

    TransactionStatusEnum(String value) {
        this.value = value;
    }
}
