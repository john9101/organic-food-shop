package com.spring.project.organicfoodshop.util;

import com.spring.project.organicfoodshop.util.constant.TargetSubjectEnum;

public class FormatErrorContentUtil {

    public static String decorateNotFoundEntityErrorContent(String attributeName, String attributeValue, TargetSubjectEnum targetSubjectEnum) {
        return String.format("Không tìm thấy %s với %s là %s", targetSubjectEnum.getDisplayName() , attributeName, attributeValue);
    }

    public static String decorateExistEntityErrorContent(String attributeName, String attributeValue, TargetSubjectEnum targetSubjectEnum) {
        return String.format("Đã tồn tại %s với %s là %s", targetSubjectEnum.getDisplayName() , attributeName, attributeValue);
    }
}
