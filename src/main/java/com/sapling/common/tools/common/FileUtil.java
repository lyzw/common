package com.sapling.common.tools.common;

import java.io.*;
import java.rmi.AccessException;
import java.util.UUID;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/10
 * @since v1.0
 */
public class FileUtil {

    public static final Integer K = 1024;
    public static final Integer M = 1024 * K;
    public static final Integer G = 1024 * M;


    public static final Integer maxByteSize = 10 * M;


    public static byte[] toBytes(File file) throws IOException {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        if (!file.canRead()) {
            throw new AccessException("access permission error! ");
        }
        if (file.getTotalSpace() > maxByteSize) {
            return null;
        } else {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return bytes;
        }
    }

    public static File writeTempFile(byte[] bytes) throws IOException {
        String tempPath = System.getProperty("java.io.tmpdir");
        if (!tempPath.endsWith(File.separator)){
            tempPath = tempPath + File.pathSeparator;
        }
        String fileName = tempPath + UUID.randomUUID().toString();
        File tempFile = new File(fileName);
        BufferedOutputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            bufferedInputStream.write(bytes);
            bufferedInputStream.flush();
        }finally {
            try{
                bufferedInputStream.close();
            }catch (Exception e){}
        }
        return tempFile;
    }

    public static void main(String[] args) throws IOException {
        File file = writeTempFile("1234567890-sdfghjkl;xcvbnm,.34567890pokmnbgt678i".getBytes());
        System.out.println(file.getName());
    }
}
