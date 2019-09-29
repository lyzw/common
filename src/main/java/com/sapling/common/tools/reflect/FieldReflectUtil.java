package com.sapling.common.tools.reflect;

import com.sapling.common.tools.common.StringUtil;
import com.sun.org.apache.regexp.internal.RE;

import java.lang.reflect.Field;

/**
 * 属性反射工具类
 *
 * @author wei.zhou
 * @date 2019/9/25
 * @since 1.0
 */
public class FieldReflectUtil {

    /**
     * 获取属性
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static Field getField(String fieldName, Object object) {
        Class clazz = object.getClass();
        return getField(fieldName, object);
    }

    /**
     * 获取属性
     *
     * @param fieldName
     * @param clazz
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getField(String fieldName, Class clazz) throws NoSuchFieldException {
        if (clazz == null || StringUtil.isEmpty(fieldName)) {
            return null;
        }
        Field field = null;
        try {
            field = clazz.getField(fieldName);
        } catch (NoSuchFieldException e) {
            if (clazz.getSuperclass() != null) {
                field = getField(fieldName, clazz.getSuperclass());
            }
        }
        if (field == null) {
            throw new NoSuchFieldException();
        }
        return field;
    }

    /**
     * 获取属性的值
     *
     * @param fieldName 属性名
     * @param object    属性所在的对象
     * @return 属性的值
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public static Object getFieldValue(String fieldName, Object object)
            throws IllegalAccessException, NoSuchFieldException {
        Field field = getField(fieldName, object);
        if (field == null) {
            throw new NoSuchFieldException();
        }
        field.setAccessible(true);
        return field.get(object);
    }

}
