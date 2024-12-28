package com.spring.project.organicfoodshop.util;

import com.spring.project.organicfoodshop.util.constant.MeasurementUnitEnum;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;
import com.spring.project.organicfoodshop.util.constant.SellingUnitEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;

public class FormatterUtil {

    public static String formatNotFoundExceptionMessage(String attributeName, Object attributeValue, ModuleEnum moduleEnum) {
        return String.format("Không tìm thấy %s với %s là %s", moduleEnum.getName(), attributeName, attributeValue);
    }

    public static String formateExistExceptionMessage(String attributeName, Object attributeValue, ModuleEnum moduleEnum) {
        return String.format("Đã tồn tại %s với %s là %s", moduleEnum.getName() , attributeName, attributeValue);
    }

    public static String formatProductTitle(String name, SellingUnitEnum sellingUnitEnum, Double measurementValue, MeasurementUnitEnum measurementUnitEnum) {
        return String.format("%s (%s%s%s)", name, (sellingUnitEnum == null ? "" : sellingUnitEnum.getName() + " "), DecimalFormat.getInstance().format(measurementValue), measurementUnitEnum.name());
    }
}