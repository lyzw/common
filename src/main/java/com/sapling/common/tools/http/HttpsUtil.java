package com.sapling.common.tools.http;


import com.sapling.common.tools.common.StringUtil;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class HttpsUtil {
    private static final String METHOD_POST = "POST";
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final int connectTimeout = 6000;
    private static final int readTimeout = 6000;

    public static String doPost(String url, String str) {
        String charset = DEFAULT_CHARSET;
        String ctype = "application/json;charset=" + charset;
        HttpsURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        byte[] content = null;
        try {
            content = str.getBytes(charset);
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(ctx);


            conn = getConnection(new URL(url), METHOD_POST, ctype);
            conn.setHostnameVerifier(new HostnameVerifier() {


                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);


            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
        } catch (Exception e) {
//            log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
        } finally {
            try {
                if (out != null) out.close();
                if (conn != null) conn.disconnect();
            } catch (Exception e) {
//                log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
            }
        }


        return rsp;
    }

    public static String get(String path) throws Exception {
        HttpsURLConnection httpConn = null;
        BufferedReader in = null;
        try {
            URL url = new URL(path);
            httpConn = (HttpsURLConnection) url.openConnection();


            //读取响应
            if (httpConn.getResponseCode() == 200) {
                StringBuffer content = new StringBuffer();
                String tempStr = "";
                in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                while ((tempStr = in.readLine()) != null) {
                    content.append(tempStr);
                }
                return content.toString();
            } else {
//                log.error("请求出现问题");
            }
        } catch (IOException e) {
//            log.error("IOException:", e.getMessage(),e);
        } finally {
            in.close();
            httpConn.disconnect();
        }
        return null;
    }

    public static String doDelete(String url, String str) {


        String charset = DEFAULT_CHARSET;
        String ctype = "application/json;charset=" + charset;
        HttpsURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        byte[] content = null;
        try {
            content = str.getBytes(charset);
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
            SSLContext.setDefault(ctx);


            conn = getConnection(new URL(url), "DELETE", ctype);
            conn.setHostnameVerifier(new HostnameVerifier() {


                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);


            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);
        } catch (Exception e) {
//            log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
        } finally {


            try {
                if (out != null) out.close();
                if (conn != null) conn.disconnect();
            } catch (Exception e) {
//                log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
            }
        }


        return rsp;

    }


    private static class DefaultTrustManager implements X509TrustManager {


        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }

    private static HttpsURLConnection getConnection(URL url, String method, String ctype) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
//        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
//        conn.setRequestProperty("User-Agent", "stargate");
        conn.setRequestProperty("Content-Type", ctype);
        return conn;
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtil.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (!StringUtil.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtil.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }

    /**
     * 从HttpServletRequest中获取body体中的数据
     *
     * @param request 请求
     * @return 请求体中的内容
     * @throws IOException IO异常
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        if (request == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        InputStream inputStream = request.getInputStream();
        if (inputStream == null){
            return null;
        }
        byte[] bytes = new byte[1024];
        while (inputStream.read(bytes) != -1) {
            sb.append(new String(bytes));
        }
        return sb.toString();
    }
}