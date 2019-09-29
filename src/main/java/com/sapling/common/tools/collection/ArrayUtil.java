package com.sapling.common.tools.collection;

import java.util.List;

/**
 * list util
 *
 * @author weizhou
 * @version v1.0
 * @date 2018/7/2
 * @since v1.0
 */
public class ArrayUtil {

    /**
     * Transfer list to string, with separator to join in each item
     *
     * @param arrays
     * @param join
     * @return
     */
    public static String join(List<Object> arrays, String join) {
        StringBuilder sb = new StringBuilder();
        arrays.forEach(item -> sb.append(String.valueOf(item)).append(join));
        return sb.substring(0, sb.length() - 1);
    }

    public static Object[] listToArray(List<?> list) {
        Object[] ret = new Object[list.size()];
        ret = list.toArray(ret);
        return ret;
    }

}
