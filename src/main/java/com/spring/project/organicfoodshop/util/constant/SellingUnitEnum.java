package com.spring.project.organicfoodshop.util.constant;

import lombok.Getter;

@Getter
public enum SellingUnitEnum {
    TRAY("khay"),
    BOTTLE("chay"),
    BOX("hộp"),
    BAG("túi"),
    BUNCH("nải"),
    BUNDLE("bó"),
    PIECE("miếng"),
    CARTON("vỉ"),
    PACK("gói"),
    CAN("lon"),
    JAR("lọ");

    private final String name;
    SellingUnitEnum(String name) {
        this.name = name;
    }
}
