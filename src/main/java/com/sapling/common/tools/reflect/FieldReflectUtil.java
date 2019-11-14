package com.sapling.common.tools.reflect;

import com.sapling.common.base.api.response.BaseResponse;
import com.sapling.common.tools.common.StringUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 属性反射工具类
 *
 * @author wei.zhou
 * @date 2019/9/25
 * @since 1.0
 */
public class FieldReflectUtil {

    /**
     * 递归声明的属性（优先从子类中获取，子类中获取不到则从父类中获取）
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static Field getDeclaredFieldWithRecursion(String fieldName, Object object) throws NoSuchFieldException {
        Class clazz = object.getClass();
        return getDeclaredFieldWithRecursion(fieldName, clazz);
    }

    /**
     * 获取属性
     *
     * @param fieldName
     * @param clazz
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getDeclaredFieldWithRecursion(String fieldName, Class clazz) throws NoSuchFieldException {
        if (clazz == null || StringUtil.isEmpty(fieldName)) {
            return null;
        }
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            if (clazz.getSuperclass() != null) {
                field = getDeclaredFieldWithRecursion(fieldName, clazz.getSuperclass());
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
    public static Object getRecursionDeclaredFieldValue(String fieldName, Object object)
            throws IllegalAccessException, NoSuchFieldException {
        Field field = getDeclaredFieldWithRecursion(fieldName, object);
        return getFieldValue(field, object);
    }

    public static Object getFieldValue(Field field, Object object)
            throws IllegalAccessException, NoSuchFieldException {
        if (field == null) {
            throw new NoSuchFieldException();
        }
        field.setAccessible(true);
        return field.get(object);
    }

    public static void setRecursionDeclaredFieldValue(String fieldName, Object object, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = getDeclaredFieldWithRecursion(fieldName, object);
        setFieldValue(field, object, value);
    }

    public static void setFieldValue(Field field, Object object, Object value) throws NoSuchFieldException, IllegalAccessException {
        if (field == null) {
            throw new NoSuchFieldException();
        }
        field.setAccessible(true);
        field.set(object, value);
    }

    /**
     * set null field to default value
     *
     * @param fieldName
     * @param object
     * @param defaultValue
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static void setNullRecursionDeclaredFieldToDefault(String fieldName, Object object, Object defaultValue) throws NoSuchFieldException, IllegalAccessException {
        if (getRecursionDeclaredFieldValue(fieldName, object) == null) {
            setRecursionDeclaredFieldValue(fieldName, object, defaultValue);
        }
    }

    /**
     * set null field to default value
     *
     * @param field        the field of the Object
     * @param object       the target object
     * @param defaultValue default value when the value of the field is null
     * @throws NoSuchFieldException   throw {@link NoSuchFieldException} when field is null or the the field is not belong to the target object
     * @throws IllegalAccessException throw {@link IllegalAccessException} when have no permission to the field
     */
    public static void setNullFieldToDefault(Field field, Object object, Object defaultValue) throws NoSuchFieldException, IllegalAccessException {
        if (getFieldValue(field, object) == null) {
            setFieldValue(field, object, defaultValue);
        }
    }

    /**
     * getting declared fields Recursion(include subclasses)
     *
     * @param object target object
     * @return declared field set
     */
    public static Set<Field> getDeclaredFieldsRecursion(Object object) {
        Set<Field> result = new HashSet();
        if (object == null || object.getClass().equals(Object.class)) {
            return result;
        }
        Class clazz = object.getClass();
        Field[] fields = object.getClass().getDeclaredFields();
        Set<Field> subFields = getSubClassDeclaredFieldsRecursion(clazz);
        result.addAll(subFields);
        result.addAll(Arrays.asList(fields));
        return result;
    }

    public static Set<Field> getSubClassDeclaredFieldsRecursion(Class clazz) {
        Set<Field> result = new HashSet();
        if (clazz == null || clazz.equals(Object.class)) {
            return result;
        } else {
            Class subClazz = clazz.getSuperclass();
            if (subClazz.equals(Object.class)){
                return result;
            }
            Field[] fields = subClazz.getDeclaredFields();
            Set<Field> subFields = getSubClassDeclaredFieldsRecursion(subClazz);
            result.addAll(subFields);
            result.addAll(Arrays.asList(fields));
            return result;
        }
    }

    public static void setNullRecursionDeclaredFieldsToDefault(Object object, Object defaultValue) {
        Field[] fields = object.getClass().getDeclaredFields();
        Arrays.asList(fields).forEach(field -> {
            Class fieldType = field.getType();
            if (defaultValue.getClass() == fieldType) {
                try {
                    setNullFieldToDefault(field, object, defaultValue);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void setNullRecursionDeclaredFieldsToDefault(String[] fieldNames, Object object, Object defaultValue) {
        Arrays.asList(fieldNames).forEach(fieldName -> {
            try {
                Field field = FieldReflectUtil.getDeclaredFieldWithRecursion(fieldName, object);
                if (field == null) {
                    return;
                }
                Class fieldType = field.getType();
                if (defaultValue.getClass() == fieldType) {
                    try {
                        setNullFieldToDefault(field, object, defaultValue);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
    }


    public static void main(String[] args) {
        BaseResponse baseResponse = new BaseResponse();
        setNullRecursionDeclaredFieldsToDefault(baseResponse, "123");
        System.out.println(baseResponse.toPrintString());
    }
}
