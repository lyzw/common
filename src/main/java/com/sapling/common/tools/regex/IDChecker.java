package com.sapling.common.tools.regex;

import java.util.Arrays;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/9/6
 * @since v1.0
 */
public class IDChecker extends SimpleStringChecker {

    private String defaultRegex = RegexConstants.REGEX_ID_CARD_18;

    public static final int CHECK_TYPE_18 = 1;

    public static final int CHECK_TYPE_15 = 2;

    public IDChecker(int type) {
        switch (type) {
            case CHECK_TYPE_18: {
                regex = RegexConstants.REGEX_ID_CARD_18;
                break;
            }
            case CHECK_TYPE_15: {
                regex = RegexConstants.REGEX_ID_CARD_18;
                break;
            }
            default: {
                regex = RegexConstants.REGEX_ID_CARD_18;
            }

        }
    }


    /**
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2.将这17位数字和系数相乘的结果相加。
     * 3.用加出来和除以11，看余数是多少？
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     *
     * @param value
     * @return
     */
    @Override
    boolean internalCheck(String value) {
        int[] ints = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] signs = {'1','0','X','9','8','7','6','5','4','3','2'};
        char[] chars = value.toCharArray();
        int sign = 0;
        for (int index = 0; index < chars.length - 1; index++) {
            sign += Integer.parseInt("" + chars[index]) * ints[index];
        }
        return signs[sign%11] == Character.toUpperCase(chars[chars.length-1]);
    }

    public static void main(String[] args) {
        IDChecker idChecker = new IDChecker(1);
        System.out.println(idChecker.check("43018119870805147x"));
    }
}
