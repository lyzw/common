package com.sapling.common.tools.http;

import com.sapling.common.tools.io.NormalIOUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * 请求内容重读请求
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/10/16.
 * @since v1.0
 */
public class ReReadContentHttpServletRequest extends HttpServletRequestWrapper {

    private static Logger logger = LoggerFactory.getLogger(ReReadContentHttpServletRequest.class);


    private byte[] bodyConent;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public ReReadContentHttpServletRequest(HttpServletRequest request) {
        super(request);
        try {
            bodyConent = NormalIOUtil.toBytes(request.getInputStream());
        } catch (IOException e) {
            bodyConent = null;
        }
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(bodyConent);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

    /**
     * 获取请求体的内容
     * @return
     * @throws UnsupportedEncodingException
     */
    public String getBodyContent() throws UnsupportedEncodingException {
        return new String(bodyConent,this.getCharacterEncoding());
    }

}
