package com.sapling.common.tools.common;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/6
 * @since v1.0
 */
public class ByteUtil {


    public static byte[] shortToBytes(short value) {
        byte[] bytes = new byte[2];
        bytes[0] = (byte) ((value >>> 8) & 0xff);
        bytes[1] = (byte) ((value >>> 0) & 0xff);
        return bytes;
    }

    public static Short bytesToShort(byte[] bytes) {
        if (bytes.length == 2) {
            int a = (bytes[1] & 0xff) << 0;
            int b = (bytes[0] & 0xff) << 8;
            return new Integer((a | b)).shortValue();
        }
        return null;
    }

    public static byte[] intToBytes(Integer value) {
        byte[] result = new byte[4];
        result[0] = (byte) ((value >>> 24) & 0xff);
        result[1] = (byte) ((value >>> 16) & 0xff);
        result[2] = (byte) ((value >>> 8) & 0xff);
        result[3] = (byte) ((value >>> 0) & 0xff);
        return result;
    }

    public static Integer bytesToInt(byte[] arrays) {
        if (arrays.length == 4) {
            int a = (arrays[3] & 0xff) << 0;
            int b = (arrays[2] & 0xff) << 8;
            int c = (arrays[1] & 0xff) << 16;
            int d = (arrays[0] & 0xff) << 24;
            return a | b | c | d;
        }
        return null;
    }

    public static byte[] longToBytes(long value) {
        byte[] bytes = new byte[8];
        bytes[0] = (byte) ((value >>> 56) & 0xff);
        bytes[1] = (byte) ((value >>> 48) & 0xff);
        bytes[2] = (byte) ((value >>> 40) & 0xff);
        bytes[3] = (byte) ((value >>> 32) & 0xff);
        bytes[4] = (byte) ((value >>> 24) & 0xff);
        bytes[5] = (byte) ((value >>> 16) & 0xff);
        bytes[6] = (byte) ((value >>> 8) & 0xff);
        bytes[7] = (byte) ((value >>> 0) & 0xff);
        return bytes;
    }

    public static Long bytesToLong(byte[] arrays) {
        if (arrays.length == 8) {
            return ((long) (arrays[7] & 0xff) << 0
                    | (long) (arrays[6] & 0xff) << 8
                    | (long) (arrays[5] & 0xff) << 16
                    | (long) (arrays[4] & 0xff) << 24
                    | (long) (arrays[3] & 0xff) << 32
                    | (long) (arrays[2] & 0xff) << 40
                    | (long) (arrays[1] & 0xff) << 48
                    | (long) (arrays[0] & 0xff) << 56);
        }
        return null;
    }


    public static String bytesToHex(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] hexToBytes(String hex) {
        if (hex.length() % 2 != 0) {
            hex = "0" + hex;
        }
        byte[] bytes = new byte[hex.length() / 2];
        //1
        char[] chars = hex.toCharArray();
        int cindex = 0;
        for (int index = 0; index < chars.length / 2; index++) {
            bytes[cindex++] = (byte) Integer.parseInt(chars[index * 2] + "" + chars[index * 2 + 1], 16);
        }
        return bytes;
    }

    char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    public static void main(String[] args) {
        short value = 20;
        System.out.println(Integer.toHexString(value));
        System.out.println(bytesToHex(shortToBytes(value)));
        System.out.println(bytesToShort(hexToBytes(bytesToHex(shortToBytes(value)))));
        System.out.println(bytesToShort(shortToBytes(value)));


    }
}
