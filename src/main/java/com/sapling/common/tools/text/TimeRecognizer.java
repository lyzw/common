package com.sapling.common.tools.text;


import com.sapling.common.tools.common.IntegerUtil;
import com.sapling.common.tools.common.StringUtil;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间识别工具类
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2018/1/8.
 * @since v1.0
 */
public class TimeRecognizer {

    /** 小时分割符 */
    private static String hourFlagRegex = "(早晨|上午|中午|下午|傍晚|晚上|凌晨)?(\\d*)[点|\\.|:].*";
    private static String hourFlagRegex2 = "(早晨|上午|中午|下午|傍晚|晚上|凌晨)?(\\d*)[点|\\.|:]((\\d*)(刻|分)?)?.*";


    private static Calendar parseHour(Calendar calendar, String value)  {
        Pattern pattern = Pattern.compile(hourFlagRegex2);
        Matcher matcher = pattern.matcher(value);
        if (matcher.matches()) {
            System.out.println(matcher.groupCount());
            for (int index = 0; index <= matcher.groupCount(); index++) {
                System.out.println(matcher.group(index));
            }

            String preffix = matcher.group(1);
            String hourValue = matcher.group(2);
            String minuterValue = matcher.group(4);
            String minuterUnit = matcher.group(5);
            Integer hour = IntegerUtil.parseToInteger(hourValue);
            Integer minuter = IntegerUtil.parseToInteger(minuterValue, 0);
            if (!StringUtil.isEmpty(preffix)) {
                ChinesePmAndAmEnum item = ChinesePmAndAmEnum.getEnumByDesc(preffix);
                if (item != null && item.code == 2) {
                    if (hour < 12) {
                        hour = hour + 12;
                    }
                }
            }
            if (!StringUtil.isEmpty(minuterUnit)) {
                ChineseMinuterEnum item = ChineseMinuterEnum.getEnumByDesc(minuterUnit);
                if (item != null) {
                    minuter = minuter * item.code;
                }
            }
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minuter);
        }
        return calendar;
    }

    private static Calendar parseMinuters(Calendar calendar,String value){
        return calendar;
    }

    public static void main(String[] args)  {
        String value = "下午14点12分";
        System.out.println(parseHour(Calendar.getInstance(), value).getTime());
    }

    enum ChineseMinuterEnum{
        /*注意，新增的时候，code必须是当前的值相乘的值*/
        MINUTER(1,"分"),
        MINUTER2(1,"分钟"),
        QUARTER(15,"刻"),
        QUARTER2(15,"刻钟"),
        ;
        private Integer code;
        private String desc;

        ChineseMinuterEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static ChineseMinuterEnum getEnumByDesc(String desc) {
            if (StringUtil.isEmpty(desc)) {
                return null;
            }
            for (ChineseMinuterEnum item : ChineseMinuterEnum.values()) {
                if (desc.equals(item.desc)) {
                    return item;
                }
            }
            return null;
        }
    }

    enum ChinesePmAndAmEnum {
        /*注意，新增的时候，code 1代表上午，2代表下午 */
        EARLY_MORNING(1, "凌晨"),
        MORNING1(1, "早晨"),
        MORNING2(1, "早上"),
        MORNING3(1, "上午"),
        AFTERNOON(2, "下午"),
        DUSK(2, "傍晚"),
        NIGHT(2, "晚上"),
        EVENING(2, "夜里"),;

        private Integer code;
        private String desc;

        ChinesePmAndAmEnum(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static ChinesePmAndAmEnum getEnumByDesc(String desc) {
            if (StringUtil.isEmpty(desc)) {
                return null;
            }
            for (ChinesePmAndAmEnum item : ChinesePmAndAmEnum.values()) {
                if (desc.equals(item.desc)) {
                    return item;
                }
            }
            return null;
        }
    }

}
