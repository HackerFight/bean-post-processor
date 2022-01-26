package com.qiuguan.boot.biz.util;

/**
 * @author created by qiuguan on 2022/1/25 18:16
 */
public class StringUtil {

    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    public static boolean isBlank(String str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for(int i = 0; i < length; ++i) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
}
