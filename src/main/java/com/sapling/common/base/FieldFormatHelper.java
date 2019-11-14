package com.sapling.common.base;

import com.sapling.common.base.api.response.BasePageResponse;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2019/11/12
 * @since 1.0
 */
public class FieldFormatHelper {
    public static final String DOUBLE_QUOTATION_MARK = "\"";

    public static String appendFieldName(Field field){
        StringBuilder sb = new StringBuilder();
        sb.append(DOUBLE_QUOTATION_MARK)
                .append(field.getName())
                .append(DOUBLE_QUOTATION_MARK)
                .append(":");
        return sb.toString();
    }


    public static void main(String[] args) {
        BasePageResponse<String> response = new BasePageResponse();
        response.setData(Arrays.asList("1","2","3"));
        System.out.println(response.toJsonString());
    }
}
