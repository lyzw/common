package com.sapling.common.tools.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/9/5
 * @since v1.0
 */
public class RegexConstants {


    public static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    public static final String REGEX_ID_CARD_18 = "^(\\d{6})(((18|19|20)\\d{2})(10|11|12|0[0-9])(0[1-9]|[12][0-9]|3[01]))\\d{3}[0-9xX].*$";

    public static final String REGEX_MOBILE = "1\\d{10}";

    public static final String REGEX_ ="";
//    public static final String REGEX_ ="";
//    public static final String REGEX_ ="";

    public static void main(String[] args) {
        String regex = "(0[1-9]|[12][0-9]|3[01])";
        Pattern pattern = Pattern.compile(REGEX_ID_CARD_18);
        Matcher matcher = pattern.matcher("401190198709091230111x");
        System.out.println(matcher.matches());
        if (matcher.matches()){
            for (int i = 0;i<=matcher.groupCount();i++){
                System.out.println(i+ ":" + matcher.group(i));
            }
        }
    }

}
