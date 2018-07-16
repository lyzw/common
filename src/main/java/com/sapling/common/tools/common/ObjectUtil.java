package com.sapling.common.tools.common;

import java.io.*;
import java.util.Optional;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
public class ObjectUtil {

    /**
     * 深度复制
     *
     * @param object 待复制对象
     * @return 深度复制后的对象
     */
    public static Object deepCopy(Object object) {
        Object retObject = null;
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(object);
            outputStream.flush();

            inputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            retObject = inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {

            }
        }
        return retObject;
    }

    /**
     * 字节数组转换为指定的类：
     * <ul>
     * <li>字符数组为空或者长度为0，则返回Optional.empty{@link Optional#empty()} </li>
     * <li>字节数组不能转换为类实例，则抛出ClassNotFoundException{@link ClassNotFoundException}</li>
     * <li>字节数组转换后的类不是指定对象的实例，则抛出ClassCastException异常{@link ClassCastException}</li>
     * <li>都符合的情况下，返回Optional.of()指定类型的实例</li>
     * </ul>
     *
     * @param bytes 字节数组
     * @param clazz 指定的类型
     * @return Optional
     * @throws IOException            IO异常
     * @throws ClassNotFoundException 类型找不到异常
     * @throws ClassCastException     类型转换异常
     */
    public static Optional bytesToObject(byte[] bytes, Class clazz) throws IOException, ClassNotFoundException, ClassCastException {
        if (bytes == null || bytes.length == 0) {
            return Optional.empty();
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Object object = objectInputStream.readObject();
        if (clazz.isInstance(object)) {
            return Optional.of(object);
        } else {
            throw new ClassCastException();
        }
    }

    /**
     * 对象实体转换为字节数组
     *
     * @param object 对象实体
     * @return 字节数组
     * @throws IOException IO异常
     */
    public static byte[] ObjectToBytes(Object object) throws IOException {
//        if (object == null) {
//            return null;
//        }
        byte[] bytes = null;
        ObjectOutputStream outputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            outputStream = new ObjectOutputStream(byteArrayOutputStream);
            outputStream.writeObject(object);
            outputStream.flush();
            bytes = byteArrayOutputStream.toByteArray();
            return bytes;
        } finally {
            outputStream.close();
            byteArrayOutputStream.close();
        }
    }
}
