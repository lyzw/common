package com.sapling.common.tools.http;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/1
 * @since v1.0
 */
public class HttpResponseUtil {

    public static final String DEFAULT_CHARSET = "utf-8";

    public static boolean isOk(HttpResponse httpResponse) {
        if (httpResponse == null) {
            return false;
        }
        if (httpResponse.getStatusLine() == null) {
            return false;
        }
        if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            return false;
        }
        return true;
    }

    public static String stringContent(HttpResponse httpResponse) throws IOException {
        if (isOk(httpResponse)) {
            return EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
        }
        return null;
    }
}
