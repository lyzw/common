package com.sapling.common.tools.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/24
 * @since v1.0
 */
public class RegexUtil {

    public static void main(String[] args) {
        String regex = "(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\d+)\\s(\\?|\\d+)\\s(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher("* 0 1 1 1 ? 2018");
        System.out.println(matcher.matches());

    }
}
