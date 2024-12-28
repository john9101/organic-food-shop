package com.spring.project.organicfoodshop.util;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Function;

public class EnumUtil {
    public static <E extends Enum<E>,T> E getEnumByAttributeValue(Class<E> enumClass, T attributeValue, Function<E, T> attributeGetter) {
        if (attributeValue == null) return null;
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> attributeValue.equals(getAttributeValueByEnum(e, attributeGetter)))
                .findFirst().orElse(null);
    }

    public static <E extends Enum<E>, T> T getAttributeValueByEnum(E e, Function<E, T> attributeGetter) {
        if (e == null || attributeGetter == null) return null;
        return attributeGetter.apply(e);
    }

}
