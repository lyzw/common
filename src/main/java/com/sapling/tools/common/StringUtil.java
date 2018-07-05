package com.sapling.tools.common;

import java.util.Arrays;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/6
 * @since v1.0
 */
public class StringUtil {

    private static final String UNDER_LINE = "_";

    public static boolean isEmpty(String value) {
        return !(value != null && !"".equals(value));
    }

    public static String upperFirstLetter(String value) {
        if (isEmpty(value)) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(value.substring(0, 1).toUpperCase()).append(value.substring(1));
        return sb.toString();
    }

    public static String underLineToCamel(String value) {
        if (isEmpty(value)) {
            return value;
        }
        if (!value.contains(UNDER_LINE)) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        String[] arrays = value.split(UNDER_LINE);
        sb.append(arrays[0]);
        Arrays.asList(arrays).subList(1, arrays.length).forEach(item -> sb.append(upperFirstLetter(item)));
        return sb.toString();
    }


}
