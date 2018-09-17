package com.sapling.common.tools.regex;

import java.util.regex.Pattern;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/24
 * @since v1.0
 */
public class RegexUtil {

    /**
     * 校验当前给定的值是否符合特定的正则表达式
     *
     * @param regex 正则表达式
     * @param value 给定的值
     * @return 是否符合
     */
    public static boolean isMatch(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(value).matches();
    }
}
