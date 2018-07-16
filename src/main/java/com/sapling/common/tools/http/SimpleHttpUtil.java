package com.sapling.common.tools.http;

import com.sapling.common.tools.common.StringUtil;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/6/29
 * @since v1.0
 */
public class SimpleHttpUtil {

    /**
     * 发送get请求
     *
     * @param httpClient
     * @param url        请求完成链接（如果包含有参数，请将参数拼接到URL后）
     * @param headers    请求头
     * @return 文本返回
     * @throws IOException
     */
    public static String doGet(HttpClient httpClient, String url, Header[] headers) throws IOException {
        HttpResponse response = doGet(httpClient, url, null, headers);
        return HttpResponseUtil.stringContent(response);
    }

    /**
     * @param httpClient
     * @param url        请求链接
     * @param paramters  参数列表
     * @param headers    请求头信息列表
     * @return http返回
     * @throws IOException
     */
    public static HttpResponse doGet(HttpClient httpClient, String url, Map<String, String> paramters, Header... headers) throws IOException {
        HttpGet httpGet = new HttpGet();
        return doRequst(httpClient, httpGet, url, paramters, headers);
    }

    public static HttpResponse doDelete(HttpClient httpClient, String url, Map<String, String> paramters, Header... headers) throws IOException {
        HttpDelete httpGet = new HttpDelete();
        return doRequst(httpClient, httpGet, url, paramters, headers);
    }

    private static HttpResponse doRequst(HttpClient httpClient, HttpRequestBase httpRequest, String url, Map<String, String> paramters, Header... headers) throws IOException {
        String uri = genGetUrlWithParamters(url, paramters);
        httpRequest.setURI(URI.create(uri));
        setHeaders(httpRequest, headers);
        return httpClient.execute(httpRequest);
    }

    public static HttpResponse doFormPost(HttpClient httpClient, String url, Map<String, String> paramters, Header... headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        return doFormRequest(httpClient, httpPost, paramters, headers);
    }

    public static HttpResponse doFormPut(HttpClient httpClient, String url, Map<String, String> paramters, Header... headers) throws IOException {
        HttpPut httpPut = new HttpPut(url);
        return doFormRequest(httpClient, httpPut, paramters, headers);
    }

    public static HttpResponse doBodyPost(HttpClient httpClient, String url, String content, Header... headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        return doBodyReqeust(httpClient, httpPost, content, headers);
    }

    protected static HttpResponse doBodyReqeust(HttpClient httpClient, HttpEntityEnclosingRequestBase httpRequest, String content, Header... headers) throws IOException {
        setHeaders(httpRequest, headers);
        if (!StringUtil.isEmpty(content)) {
            StringEntity stringEntity = new StringEntity(content);
            httpRequest.setEntity(stringEntity);
        }
        return httpClient.execute(httpRequest);
    }

    public static HttpResponse doFormRequest(HttpClient httpClient, HttpEntityEnclosingRequestBase httpRequest, Map<String, String> paramters, Header... headers) throws IOException {
        if (paramters != null && paramters.size() > 0) {
            HttpEntity httpEntity = new UrlEncodedFormEntity(genFormParams(paramters));
            httpRequest.setEntity(httpEntity);
        }
        setHeaders(httpRequest, headers);
        return httpClient.execute(httpRequest);
    }

    public static List<NameValuePair> genFormParams(Map<String, String> paramters) {
        List<NameValuePair> list = new ArrayList<>();
        paramters.entrySet().forEach(entity -> list.add(new BasicNameValuePair(entity.getKey(), entity.getValue())));
        return list;
    }

    private static void setHeaders(HttpRequest httpRequest, Header... headers) {
        if (headers != null && headers.length > 0) {
            httpRequest.setHeaders(headers);
        }
    }

    public static String genGetUrlWithParamters(String url, Map<String, String> paramters) {
        StringBuilder sb = new StringBuilder();
        paramters = new TreeMap<>(paramters);
        sb.append(url);
        if (paramters != null && paramters.size() > 0) {
            if (!url.contains("?")) {
                sb.append("?");
            } else {
                sb.append("&");
            }
            paramters.entrySet().forEach(entity -> sb.append(entity.getKey()).append("=").append(entity.getValue()).append("&"));
        }
        return sb.substring(0, sb.length() - 1);
    }


}
