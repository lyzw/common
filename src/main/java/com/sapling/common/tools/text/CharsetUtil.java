package com.sapling.common.tools.text;

/**
 * //TODO {Please input description here }
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2018/1/30.
 * @since v1.0
 */
public class CharsetUtil {

    private static final String UNICODE_PREFIX = "\\u";

    private static final String UNICODE_FORMAT_X = "\\u%04x";

    /**
     * 字符转换为unicode:字符值小于127的时候，直接使用否则，转换16进制使用
     * @param value
     * @return
     */
    public static String encodeToUnicode(String value) {
        char[] chars = value.toCharArray();
        StringBuffer unicodeValue = new StringBuffer();
        for (char c : chars) {
            if (c > 127) {
                unicodeValue.append(String.format(UNICODE_FORMAT_X,(int)c));
            } else {
                unicodeValue.append(c);
            }
        }
        return unicodeValue.toString();
    }

    public static String decodeUniCode(String value) {
        if (value.indexOf(UNICODE_PREFIX) == -1) {
            return value;
        }
        StringBuffer decodeValue = new StringBuffer();
        char[] chars = value.toCharArray();
        int index = 0;
        int length = chars.length;
        while (index < length) {
            if (chars[index] == '\\') {
                if (index + 6 > length) {
                    throw new IllegalArgumentException();
                }
                String sub = value.substring(index + 2, index + 6);
                char letter = (char) Integer.parseInt(sub, 16);
                decodeValue.append(letter);
                index += 6;
            } else {
                decodeValue.append(chars[index++]);
            }

        }
        return decodeValue.toString();

    }


}
