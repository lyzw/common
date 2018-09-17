package com.sapling.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/9/6
 * @since v1.0
 */
public class Test2 {


    public static void main(String[] args) {
        String testString = "美国佛罗里达";
        String regex = "((\\S*)[省|自治区|直辖市|市|特别行政区])?((\\S*?)[市|旗|自治区])?(.*?)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testString);

        System.out.println(matcher.matches());
        System.out.println(matcher.groupCount());

        if (matcher.matches()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println(matcher.group(i));
            }
        }


    }
}
