package com.sapling.common.tools.xml;

import com.sapling.common.tools.common.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/8/18
 * @since v1.0
 */
public class BeanToXmlSimpleUtil {

    private static final String XML_BEGIN = "<?xml version=\"1.0\" encoding=\"$CHART_SET$\"?>";
    private static final String XML_END = "</xml>";
    private static final String CDATA_BENGIN = "<![CDATA[";
    private static final String CDATA_END = "]]>";
    private static final String DEFAULT_CHARSET = "utf-8";

    public static String toXml(String rootTag, Object value, boolean isUseCData, String charset) {
        if (value == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        charset = StringUtil.isEmpty(charset) ? DEFAULT_CHARSET : charset;
        sb.append(XML_BEGIN.replace("$CHART_SET$", charset));
        sb.append(genContent(rootTag, value, isUseCData));
        sb.append(XML_END);
        return sb.toString();
    }

    public static String genContent(String rootTag, Object value, boolean isUseCData) {
        StringBuffer sb = new StringBuffer();
        sb.append("<").append(rootTag).append(">");
        if (value instanceof Number) {
            sb.append(genValueContent(value.toString(), isUseCData));
        } else if (value instanceof String) {
            sb.append(genValueContent((String) value, isUseCData));
        } else if (value instanceof List) {
            ((List) value).forEach(item -> sb.append(genContent(rootTag, item, isUseCData)));
        }
        sb.append("</").append(rootTag).append(">");
        return sb.toString();
    }

    public static String genValueContent(String value, boolean isUseCData) {
        StringBuffer sb = new StringBuffer();
        sb.append(isUseCData ? CDATA_BENGIN : "");
        if (!StringUtil.isEmpty(value)) {
            sb.append(value);
        }
        sb.append(isUseCData ? CDATA_END : "");
        return sb.toString();
    }

    public static void main(String[] args) {
        List list = Arrays.asList(new Integer[]{1,2,3,4});

        System.out.println(toXml("test", list, true, null));
    }
}
