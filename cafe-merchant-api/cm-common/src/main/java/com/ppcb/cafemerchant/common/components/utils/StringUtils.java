package com.ppcb.cafemerchant.common.components.utils;

public class StringUtils extends org.springframework.util.StringUtils {

    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static int lastIndexOf(String str, String search) {
        return str.lastIndexOf(search);
    }

    public static String substringAfterLast(String str, String search) {
        return str.substring(str.lastIndexOf(search) + search.length());
    }

    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    public static String overlay(String str, String overlay, int start, int end) {
        if (str == null) {
            return null;
        }
        if (overlay == null) {
            overlay = "";
        }
        StringBuilder sb = new StringBuilder(str);
        sb.replace(start, end, overlay);
        return sb.toString();
    }

    public static boolean isAnyBlank(String... strings) {
        for (String str : strings) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllBlank(String... strings) {
        for (String str : strings) {
            if (!isBlank(str)) {
                return false;
            }
        }
        return true;
    }

    public static String nullIfBlank(String str) {
        return isBlank(str) ? null : str;
    }
}
