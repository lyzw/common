package com.sapling.common.tools.collection;

import java.util.Arrays;
import java.util.List;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/2
 * @since v1.0
 */
public class ArrayUtil {

    public static String join(List<Object> arrays, String join) {
        StringBuilder sb = new StringBuilder();
        arrays.forEach(item -> sb.append(String.valueOf(item)).append(join));
        return sb.substring(0,sb.length()-1);
    }


    public static void main(String[] args) {
        System.out.println(join(Arrays.asList(new String[]{"a","b"}),"1"));
    }
}
