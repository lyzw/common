package com.sapling.tools.security;

import com.sapling.tools.common.ByteUtil;
import com.sapling.tools.io.NormalIOUtil;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/20
 * @since v1.0
 */
public class MD5Util {

    /**
     * MD5加密字符串
     *
     * @param content 字符串内容
     * @param charset 编码集
     * @return 加密后的字符串
     * @throws UnsupportedEncodingException 不支持的字符集
     * @throws NoSuchAlgorithmException     不支持的加密类型
     */
    public static String md5(String content, String charset) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return ByteUtil.bytesToHex(md.digest(content.getBytes(charset)));
    }

    /**
     * MD5加密字符串
     *
     * @param content 字符串内容
     * @return 加密后的字符串
     * @throws UnsupportedEncodingException 不支持的字符集
     * @throws NoSuchAlgorithmException     不支持的加密类型
     */
    public static String md5(String content) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return md5(content, "UTF-8");
    }


    /**
     * 对文件做摘要
     *
     * @param file 文件
     * @return 摘要
     * @throws IOException              IO异常
     * @throws NoSuchAlgorithmException 不支持的加密类型
     */
    public static String md5(File file) throws IOException, NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = NormalIOUtil.toBytes(fileInputStream);
            return ByteUtil.bytesToHex(md.digest(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
