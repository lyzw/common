package com.sapling.common.tools.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * DES加密
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2018/1/22.
 * @since v1.0
 */
public class DESUtil {

    public static final String KEY_ALGORITHM = "DES";
    public static final String ECB_CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
    public static final String CBC_CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";

    private static final String DEFAULT_CHARSET = "UTF-8";


    private static byte[] encrypt(byte[] data, byte[] key, String cipherType)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(cipherType);
        DESKeySpec keySpec=new DESKeySpec(key);
        SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
        SecretKey secretKey =keyFactory.generateSecret(keySpec);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /*---------------------ECB ENCRYPT ---------------------*/
    public static byte[] encryptECB(byte[] data, byte[] key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encrypt(data, key, ECB_CIPHER_ALGORITHM);
    }

    public static byte[] encryptECB(byte[] data, String key, String charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encryptECB(data, key.getBytes(charset));
    }

    public static byte[] encryptECB(String data, String key, String charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encryptECB(data.getBytes(charset), key.getBytes(charset));
    }

    public static byte[] encryptECB(String data, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encryptECB(data.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET));
    }

    /*---------------------ECB ENCRYPT ---------------------*/


    /*---------------------CBC ENCRYPT ---------------------*/
    public static byte[] encryptCBC(byte[] data, byte[] key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encrypt(data, key, CBC_CIPHER_ALGORITHM);
    }

    public static byte[] encryptCBC(byte[] data, String key, String charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encryptCBC(data, key.getBytes(charset));
    }


    public static byte[] encryptCBC(String data, String key, String charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encryptCBC(data.getBytes(charset), key.getBytes(charset));
    }

    public static byte[] encryptCBC(String data, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        return encryptCBC(data.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET));
    }

    /*---------------------CBC ENCRYPT ---------------------*/

    private static byte[] decrypt(byte[] data, byte[] key, String cipherType)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(cipherType);
        DESKeySpec keySpec=new DESKeySpec(key);
        SecretKeyFactory keyFactory=SecretKeyFactory.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret(keySpec));
        return cipher.doFinal(data);
    }


    /*---------------------ECB ENCRYPT ---------------------*/
    public static byte[] decryptECB(byte[] data, byte[] key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(data, key, ECB_CIPHER_ALGORITHM);
    }

    public static byte[] decryptECB(byte[] data, String key, String charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        return decryptECB(data, key.getBytes(charset));
    }

    public static byte[] decryptECB(String data, String key, String charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        return decryptECB(data.getBytes(charset), key.getBytes(charset));
    }

    public static byte[] decryptECB(String data, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        return decryptECB(data.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET));
    }

    /*---------------------ECB decrypt ---------------------*/


    /*---------------------CBC decrypt ---------------------*/
    public static byte[] decryptCBC(byte[] data, byte[] key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        return decrypt(data, key, CBC_CIPHER_ALGORITHM);
    }

    public static byte[] decryptCBC(byte[] data, String key, String charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        return decryptCBC(data, key.getBytes(charset));
    }


    public static byte[] decryptCBC(String data, String key, String charset)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        return decryptCBC(data.getBytes(charset), key.getBytes(charset));
    }

    public static byte[] decryptCBC(String data, String key)
            throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        return decryptCBC(data.getBytes(DEFAULT_CHARSET), key.getBytes(DEFAULT_CHARSET));
    }

    /*---------------------CBC decrypt ---------------------*/


}
