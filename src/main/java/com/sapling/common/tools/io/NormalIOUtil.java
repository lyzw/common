package com.sapling.common.tools.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 普通IO工具类
 *
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/20
 * @since v1.0
 */
public class NormalIOUtil {

    static byte[] buffer = new byte[1024];
    public static final String DEFAULT_CHARSET = "UTF-8";


    /**
     * 将InputStream中的数据转换为byte数组。
     *
     * @param inputStream 输入流
     * @return 输入流中的byte数组
     * @throws IOException IO异常
     */
    public static byte[] toBytes(InputStream inputStream) throws IOException {
        if (inputStream == null || inputStream.available() == 0) {
            return null;
        }
        byte[] ret = new byte[inputStream.available()];
        inputStream.read(ret);
        return ret;
    }

    /**
     * 将InputStream中的数据转换为String
     *
     * @param inputStream 输入流
     * @return 输入流中的字符串
     * @throws IOException IO异常
     */
    public static String toString(InputStream inputStream) throws IOException {
        return toString(inputStream, DEFAULT_CHARSET);
    }

    public static String toString(InputStream inputStream, String charset) throws IOException {
        if (inputStream == null || inputStream.available() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (inputStream.read(buffer) != -1) {
            sb.append(new String(buffer, charset));
        }
        return sb.toString();
    }

    /**
     * 输入流转输出流
     *
     * @param inputStream 输入流
     * @return 输出流
     * @throws IOException IO异常
     */
    public static OutputStream input2Output(InputStream inputStream) throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        while (inputStream.read(buffer) != -1) {
            outputStream.write(buffer);
        }
        outputStream.flush();
        return outputStream;
    }
}
