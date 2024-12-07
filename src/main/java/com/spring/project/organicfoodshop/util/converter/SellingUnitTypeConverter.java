package com.spring.project.organicfoodshop.util.converter;

import com.spring.project.organicfoodshop.util.constant.SellingUnitTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SellingUnitTypeConverter implements AttributeConverter<SellingUnitTypeEnum, String> {
    @Override
    public String convertToDatabaseColumn(SellingUnitTypeEnum sellingUnitTypeEnum) {
        return sellingUnitTypeEnum != null ? sellingUnitTypeEnum.getDisplayName() : null;
    }

    @Override
    public SellingUnitTypeEnum convertToEntityAttribute(String sellingUnitNameDb) {
        if (sellingUnitNameDb == null) {
            return null;
        }

        for (SellingUnitTypeEnum sellingUnitTypeEnum : SellingUnitTypeEnum.values()) {
            if (sellingUnitTypeEnum.getDisplayName().equals(sellingUnitNameDb)) {
                return sellingUnitTypeEnum;
            }
        }

        throw new IllegalArgumentException("Unknown: " + sellingUnitNameDb);
    }
}
