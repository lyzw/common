package com.sapling.common.tools.common;



/**
 * Integer工具类
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/5/27.
 * @since v1.0
 */
public class IntegerUtil {

    /**
     * 字符串转换为Integer
     *
     * @param value Int String值
     * @return 转换后的数字
     * @throws NumberFormatException 数字格式化异常
     */
    public static Integer parseToInteger(String value) throws  NumberFormatException {
        if (StringUtil.isEmpty(value, true)) {
            return null;
        }
        return Integer.parseInt(value.trim());
    }

    /**
     * 字符串转换为Integer
     *
     * @param value        Int String值
     * @param defaultValue 默认值
     * @return 转换后的数字
     * @throws NumberFormatException 数字格式化异常
     */
    public static Integer parseToInteger(String value, Integer defaultValue)
            throws  NumberFormatException {
        Integer retValue = parseToInteger(value);
        if (retValue == null) {
            retValue = defaultValue;
        }
        return retValue;
    }

    /**
     * 将byte数组转换成数字
     *
     * @param bytes byte数组
     * @return 数字
     * @throws NumberFormatException 数字格式化异常
     */
    public static Integer parseToInteger(byte[] bytes) throws  NumberFormatException {
        if (bytes == null) {
            return null;
        }
        String value = new String(bytes);
        if (StringUtil.isEmpty(value, true)) {
            return null;
        }
        return Integer.parseInt(value.trim());
    }

    /**
     * 转换为数字
     *
     * @param chars
     * @return
     */
    public static Integer parseToInteger(char[] chars) {
        if (chars == null) {
            return null;
        }
        String value = new String(chars);
        if (StringUtil.isEmpty(value, true)) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return null;
        }
    }


    public static Integer parse(Object value)  {
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof String) {
            return parseToInteger((String) value);
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        return null;
    }


    /**
     * 小端的数字转为为大端
     *
     * @param value 小端数字
     * @return 转换后的大端数字
     */
    public static Integer lowToInteger(Integer value) {
        return (((value & 0xFF) << 24) | (((value >> 8) & 0xFF) << 16) | (((value >> 16) & 0xFF) << 8) |
                ((value >> 24) & 0xFF));
    }

    public static int intToHex(Integer value) {

        return Integer.parseInt("e107", 16);
    }

    public static void main(String[] args) {
        System.out.println(intToHex(10));
    }
}
