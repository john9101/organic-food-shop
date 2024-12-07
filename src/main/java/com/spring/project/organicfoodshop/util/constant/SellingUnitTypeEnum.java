package com.spring.project.organicfoodshop.util.constant;

import lombok.Getter;

@Getter
public enum SellingUnitTypeEnum {
    TRAY("Khay"),
    BOTTLE("Chay"),
    BOX("Hộp"),
    BAG("Túi"),
    BUNCH("Nải"),
    BUNDLE("Bó"),
    PIECE("Miếng"),
    CARTON("Vỉ"),
    PACK("Gói"),
    CAN("Lon"),
    JAR("Lọ");

    private final String displayName;
    SellingUnitTypeEnum(String displayName) {
        this.displayName = displayName;
    }
}
