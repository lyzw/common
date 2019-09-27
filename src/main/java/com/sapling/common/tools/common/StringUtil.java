package com.sapling.common.tools.common;

import java.util.Arrays;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/6
 * @since v1.0
 */
public class StringUtil {

    private static final String UNDER_LINE = "_";


    /**
     * 判断字符串是否为空或者“”，判断是否为“”的时候会对字符串进行trim处理
     *
     * @param value 待检测字符串
     * @return 如果为空或者“”，返回true，否则返回false
     */
    public static boolean isEmpty(String value) {
        if (isNull(value)) {
            return true;
        }
        return "".equals(value.trim());
    }

    public static boolean isNotEmpty(String value){
        return !isEmpty(value);
    }


    /**
     * 检测字符串是否为空
     *
     * @param value 待检测的字符串
     * @return 如果字符串为空则
     */
    public static boolean isNull(String value) {
        return value == null ;
    }

    public static boolean isNotNull(String value){
        return !isNull(value);
    }

    /**
     * 判断字符串是否为空或者“”，判断是否为“”的时候会对字符串进行trim处理
     *
     * @param value   待检测字符串
     * @param trimOpr 是否进行trim操作
     * @return 如果为空或者“”，返回true，否则返回false
     */
    public static boolean isEmpty(String value, boolean trimOpr) {
        if (isNull(value)) {
            return true;
        }
        if (trimOpr) {
            return isEmpty(value);
        } else if ("".equals(value)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String value, boolean trimOpr){
        return !isEmpty(value,trimOpr);
    }

    /**
     * 将字符串首字母转为大写
     *
     * @param value
     * @return
     */
    public static String upperFirstLetter(String value) {
        if (isEmpty(value)) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(value.substring(0, 1).toUpperCase()).append(value.substring(1));
        return sb.toString();
    }

    /**
     * 下划线转驼峰
     * @param value
     * @return
     */
    public static String toCamel(String value) {
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

    /**
     * 驼峰转下划线
     *
     * @param value
     * @return
     */
    public static String toUnderLine(String value){
        if (isEmpty(value)) {
            return value;
        }
        StringBuilder sb = new StringBuilder();

        char[] chars = value.toCharArray();
        for (char c:chars){
            if (Character.isUpperCase(c)){
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }
    
    


}
