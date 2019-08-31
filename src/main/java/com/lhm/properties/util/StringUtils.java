package com.lhm.properties.util;

public class StringUtils {
    public static boolean isEmptyString(String value) {
        return value == null || value.isEmpty();
    }

    public static boolean isNotEmptyString(String value) {
        return !isEmptyString(value);
    }
}
