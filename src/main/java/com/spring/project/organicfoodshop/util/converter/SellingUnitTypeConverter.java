package com.spring.project.organicfoodshop.util.converter;

import com.spring.project.organicfoodshop.util.constant.SellingUnitEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SellingUnitTypeConverter implements AttributeConverter<SellingUnitEnum, String> {
    @Override
    public String convertToDatabaseColumn(SellingUnitEnum sellingUnitTypeEnum) {
        return sellingUnitTypeEnum != null ? sellingUnitTypeEnum.getName() : null;
    }

    @Override
    public SellingUnitEnum convertToEntityAttribute(String sellingUnitNameDb) {
        if (sellingUnitNameDb == null) {
            return null;
        }

        for (SellingUnitEnum sellingUnitTypeEnum : SellingUnitEnum.values()) {
            if (sellingUnitTypeEnum.getName().equals(sellingUnitNameDb)) {
                return sellingUnitTypeEnum;
            }
        }

        throw new IllegalArgumentException("Unknown: " + sellingUnitNameDb);
    }
}
