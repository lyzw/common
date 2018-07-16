package com.sapling.common.tools.text;

/**
 * //TODO {Please input description here }
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2018/1/5.
 * @since v1.0
 */
public class ChineseNumberUtil {


    private static char[] chineseNumbers = {'零', '一', '二', '三', '四', '五', '六', '七', '八', '九'};
    private static String chinseNumberStr = "零一二三四五六七八九";
    private static String DOT = "点";
    private static String TEN = "十";
    private static String HUNDRED = "百";
    private static String THOUSAND = "千";
    private static String TEN_THOUSAND = "万";
    private static String HUNDRED_MILLION = "亿";
    private static String[] cnUnits = {HUNDRED_MILLION,TEN_THOUSAND};


    public static Integer chineseNumberToInteger(String cnNumber) {
        Integer value = 0;
        Integer tenThousandIdx = cnNumber.indexOf(cnNumber);
        if (tenThousandIdx > -1) {
        }
        return null;
    }


    public static Integer parseCnNumber(String cnNumber) {
        int index = 1;
        for (String unit : cnUnits){
            parseCnNumber(cnNumber,unit, (int) Math.pow(new Double(10000),new Double(cnUnits.length-index)));
        }
        return null;
    }

    public static Integer parseCnNumber(String cnNumber,String delimiter,Integer plus) {
        if (cnNumber.indexOf(delimiter) != -1){
            String[] values = cnNumber.split(delimiter);
            Integer preValue = 0;
            if (values.length>0){
                preValue = parseLittleCnNumber(values[0]);
            }
        }
        return null;
    }


    public static Integer parseLittleCnNumber(String cnNumber){
        Integer value = 0;
        Integer thousandIdx = cnNumber.indexOf(THOUSAND);
        if (thousandIdx > -1) {
            String thousand = cnNumber.substring(0, thousandIdx);
            Integer thousandNumber = chinseNumberStr.indexOf(thousand);
            value += thousandNumber * 1000;
        }
        Integer hundredIdx = cnNumber.indexOf(HUNDRED);

        if (hundredIdx > -1) {
            String hundred = cnNumber.substring(hundredIdx - 1, hundredIdx);
            Integer hundredNumber = chinseNumberStr.indexOf(hundred);
            value += hundredNumber * 100;
        }

        Integer tenIdx = cnNumber.indexOf(TEN);
        if (tenIdx > -1) {
            String ten = cnNumber.substring(tenIdx - 1, tenIdx);
            Integer tenNumber = chinseNumberStr.indexOf(ten);
            value += tenNumber * 10;
        }
        Integer dotIdx = cnNumber.indexOf(DOT);
        if (dotIdx > -1) {
            String dotStr = cnNumber.substring(dotIdx);
            dotStr.split(".");
        }
        return value;
    }
    public static void main(String[] args) {
    }

}
