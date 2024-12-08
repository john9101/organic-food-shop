package com.spring.project.organicfoodshop.util;

import com.spring.project.organicfoodshop.util.constant.TargetSubjectEnum;

public class FormatExceptionMessageUtil {

    public static String decorateNotFoundEntityMessage(String attributeName, Object attributeValue, TargetSubjectEnum targetSubjectEnum) {
        return String.format("Không tìm thấy %s với %s là %s", targetSubjectEnum.getDisplayName() , attributeName, attributeValue.toString());
    }

    public static String decorateExistEntityMessage(String attributeName, Object attributeValue, TargetSubjectEnum targetSubjectEnum) {
        return String.format("Đã tồn tại %s với %s là %s", targetSubjectEnum.getDisplayName() , attributeName, attributeValue.toString());
    }
}
