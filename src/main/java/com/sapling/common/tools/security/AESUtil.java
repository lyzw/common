package com.sapling.common.tools.security;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * AES加密解密类
 * <p>
 * 如果使用256位的AES加密，则需要下载JDK对应的jce包，jdk替换jre\lib\security文件夹下的两个jar包，jre则替换\lib\security下的jar包
 * jdk6:<a>http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html</a>
 * jdk7:<a>http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html</a>
 * jdk8:<a>http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html</a>
 * <p>
 * </p>
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/12/22.
 * @since v1.0
 */
public class AESUtil {


    public static final String KEY_ALGORITHM = "AES";
    public static final String ECB_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    public static final String CBC_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";


    /**
     * 对给定的数据进行AES加密处理，机密类型支持AES/ECB/PKCS5Padding与AES/CBC/PKCS5Padding两种
     * @param encodeType 加密类型，暂时支持{@link AESUtil#ECB_CIPHER_ALGORITHM}与{@link AESUtil#CBC_CIPHER_ALGORITHM}两种方式
     * @param content   加密内容
     * @param key       加密key
     * @param charsets  编码集
     * @return  加密后的字节码数组
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encode(String encodeType, byte[] content, String key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(encodeType);
        cipher.init(Cipher.ENCRYPT_MODE, restoreSecretKey(key, charsets));
        return cipher.doFinal(content);
    }

    /**
     * 对给定的数据进行AES加密处理，机密类型支持AES/ECB/PKCS5Padding与AES/CBC/PKCS5Padding两种
     * @param encodeType 加密类型，暂时支持{@link AESUtil#ECB_CIPHER_ALGORITHM}与{@link AESUtil#CBC_CIPHER_ALGORITHM}两种方式
     * @param content   加密内容
     * @param key       加密key
     * @param charsets  编码集
     * @return  加密后的字节码数组
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encode(String encodeType, byte[] content, byte[] key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(encodeType);
        cipher.init(Cipher.ENCRYPT_MODE, restoreSecretKey(key));
        return cipher.doFinal(content);
    }

    /**
     * 对给定的数据进行AES解密处理，解密类型支持AES/ECB/PKCS5Padding与AES/CBC/PKCS5Padding两种
     * @param decodeType 解密密类型，暂时支持{@link AESUtil#ECB_CIPHER_ALGORITHM}与{@link AESUtil#CBC_CIPHER_ALGORITHM}两种方式
     * @param content   加密内容
     * @param key       加密key
     * @param charsets  编码集
     * @return  解密后的字节码数组
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decode(String decodeType, byte[] content, String key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(decodeType);
        cipher.init(Cipher.DECRYPT_MODE, restoreSecretKey(key, charsets));
        return cipher.doFinal(content);
    }

    /**
     * 对给定的数据进行AES解密处理，解密类型支持AES/ECB/PKCS5Padding与AES/CBC/PKCS5Padding两种
     * @param decodeType 解密密类型，暂时支持{@link AESUtil#ECB_CIPHER_ALGORITHM}与{@link AESUtil#CBC_CIPHER_ALGORITHM}两种方式
     * @param content   加密内容
     * @param key       加密key
     * @param charsets  编码集
     * @return  解密后的字节码数组
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decode(String decodeType, byte[] content, byte[] key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(decodeType);
        cipher.init(Cipher.DECRYPT_MODE, restoreSecretKey(key));
        return cipher.doFinal(content);
    }


    /**
     * AES-ECB加密
     *
     * @param content  待加密内容
     * @param key      加密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesEcbEncode(String content, String key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return aesEcbEncode(content.getBytes(charsets), key, charsets);
    }

    public static byte[] aesEcbEncode(byte[] content, String key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encode(ECB_CIPHER_ALGORITHM, content, key, charsets);
    }

    /**
     * AES-ECB加密
     *
     * @param content  待加密内容
     * @param key      加密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesEcbEncode(String content, byte[] key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return aesEcbEncode(content.getBytes(charsets), key, charsets);
    }

    public static byte[] aesEcbEncode(byte[] content, byte[] key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return encode(ECB_CIPHER_ALGORITHM, content, key, charsets);
    }


    /**
     * AES-ECB解密
     *
     * @param content  待解密的内容
     * @param key      待解密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesEcbDecode(String content, String key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return aesEcbDecode(content.getBytes(charsets), key, charsets);
    }

    /**
     * AES-ECB解密
     *
     * @param content  待解密的内容
     * @param key      待解密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesEcbDecode(byte[] content, String key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decode(ECB_CIPHER_ALGORITHM, content, key, charsets);
    }



    /**
     * AES-ECB解密
     *
     * @param content  待解密的内容
     * @param key      待解密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesEcbDecode(String content, byte[] key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return aesEcbDecode(content.getBytes(charsets), key, charsets);
    }

    /**
     * AES-ECB解密
     *
     * @param content  待解密的内容
     * @param key      待解密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesEcbDecode(byte[] content, byte[] key, String charsets)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        return decode(ECB_CIPHER_ALGORITHM, content, key, charsets);
    }

    /**
     * AES-CBC加密
     *
     * @param content  待加密的内容
     * @param key      待加密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesCbcEncode(String content, String key, String charsets)
            throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        return aesCbcEncode(content.getBytes(charsets), key, charsets);
    }

    /**
     * AES-CBC加密
     *
     * @param content  待加密的内容
     * @param key      待加密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesCbcEncode(byte[] content, String key, String charsets)
            throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        return encode(CBC_CIPHER_ALGORITHM, content, key, charsets);
    }

    /**
     * AES-CBC解密
     *
     * @param content  待解密的内容
     * @param key      待解密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesCbcDecode(String content, String key, String charsets)
            throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        return aesCbcDecode(content.getBytes(charsets), key, charsets);
    }

    /**
     * AES-CBC解密
     *
     * @param content  待解密的内容
     * @param key      待解密的key
     * @param charsets 字符集
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] aesCbcDecode(byte[] content, String key, String charsets)
            throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException {
        return decode(CBC_CIPHER_ALGORITHM, content, key, charsets);
    }


    public static SecretKey restoreSecretKey(byte[] secretBytes) {
        return new SecretKeySpec(secretBytes, KEY_ALGORITHM);
    }

    public static SecretKey restoreSecretKey(String key, String charsets) throws UnsupportedEncodingException {
        byte[] secretBytes = key.getBytes(charsets);
        return restoreSecretKey(secretBytes);
    }

}
