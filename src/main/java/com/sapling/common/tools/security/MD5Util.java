package com.sapling.common.tools.security;

import com.sapling.common.tools.common.ByteUtil;
import com.sapling.common.tools.io.NormalIOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/5/18.
 * @since v1.0
 */
public class MD5Util {

    static final String DIGEST_MD5 = "MD5";
    static final String DEFAULT_CHARSET = "utf-8";

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
        MessageDigest md = MessageDigest.getInstance(DIGEST_MD5);
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
        MessageDigest md = MessageDigest.getInstance(DIGEST_MD5);
        return ByteUtil.bytesToHex(md.digest(content.getBytes(DEFAULT_CHARSET)));

    }

    /**
     * 获取md5加密后的byte数组
     *
     * @param data 待加密数据
     * @return 加密后的byte数组
     * @throws NoSuchAlgorithmException     没有对应的算法{@link NoSuchAlgorithmException}
     * @throws UnsupportedEncodingException 不支持的字符集{@link UnsupportedEncodingException}
     */
    public static byte[] digest(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return digest(data, DEFAULT_CHARSET);
    }

    /**
     * 获取md5加密后的byte数组
     *
     * @param data    待加密数据
     * @param charset 字符集
     * @return 加密后的byte数组
     * @throws NoSuchAlgorithmException     没有对应的算法{@link NoSuchAlgorithmException}
     * @throws UnsupportedEncodingException 不支持的字符集{@link UnsupportedEncodingException}
     */
    public static byte[] digest(String data, String charset)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return digest(data.getBytes(charset));
    }


    /**
     * 获取md5加密后的byte数组
     *
     * @param data 待加密字节数组
     * @return 加密后的byte数组
     * @throws NoSuchAlgorithmException 没有对应的算法{@link NoSuchAlgorithmException}
     */
    public static byte[] digest(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(DIGEST_MD5);
        return md.digest(data);
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
            MessageDigest md = MessageDigest.getInstance(DIGEST_MD5);
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
