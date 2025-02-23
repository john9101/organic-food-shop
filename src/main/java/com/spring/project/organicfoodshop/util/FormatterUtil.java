package com.spring.project.organicfoodshop.util;

import com.spring.project.organicfoodshop.util.constant.MeasurementUnitEnum;
import com.spring.project.organicfoodshop.util.constant.ModuleEnum;

import java.text.DecimalFormat;

public class FormatterUtil {

    public static String formatNotFoundExceptionMessage(String attributeName, Object attributeValue, ModuleEnum moduleEnum) {
        return String.format("Không tìm thấy %s với %s là %s", moduleEnum.getName(), attributeName, attributeValue);
    }

    public static String formateExistExceptionMessage(String attributeName, Object attributeValue, ModuleEnum moduleEnum) {
        return String.format("Đã tồn tại %s với %s là %s", moduleEnum.getName() , attributeName, attributeValue);
    }

    public static String formatProductTitle(String name, Double measurementValue, MeasurementUnitEnum measurementUnitEnum) {
        return String.format("%s (%s%s)", name, DecimalFormat.getInstance().format(measurementValue), measurementUnitEnum.name());
    }
}