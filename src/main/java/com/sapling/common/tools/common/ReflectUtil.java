package com.sapling.common.tools.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
public class ReflectUtil {

    public static Object getInstance(Class type, Map<String, Object> values) throws IllegalAccessException, InstantiationException {
        Object instance = type.newInstance();
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            String propertyName = entry.getKey();
            Object value = entry.getValue();
            try {
                Field field = type.getDeclaredField(propertyName);
                if (field.getType().isInstance(value)) {
                    field.setAccessible(true);
                    field.set(instance, value);
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 获取对象的属性的键值对
     *
     * @param instance 对象实例
     * @return 键值对
     */
    public static Map<String, Object> getFieldValueMap(Object instance) {
        Class clazz = instance.getClass();
        Map<String, Object> retMap = new HashMap<>();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                retMap.put(field.getName(), field.get(instance));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return retMap;
    }

    /**
     * 执行方法
     *
     * @param object     对象实例
     * @param args       方法参数
     * @param methodName 方法名
     * @param argType    参数的类型
     * @return 方法的执行结果
     * @throws NoSuchMethodException     方法找不到异常
     * @throws InvocationTargetException 调用异常
     * @throws IllegalAccessException    访问异常
     */
    public static Object invokeMethod(Object object, String methodName, Object[] args, Class... argType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class clzz = object.getClass();
        Method method = clzz.getDeclaredMethod(methodName, argType);
        method.setAccessible(true);
        return method.invoke(object, args);
    }

    /**
     * 调用类的静态方法
     *
     * @param clazz      类
     * @param methodName 方法名
     * @param args       参数
     * @param argType    参数类型
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @returnø
     */
    public static Object invokeStaticMethod(Class clazz, String methodName, Object[] args, Class... argType) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = clazz.getDeclaredMethod(methodName, argType);
        method.setAccessible(true);
        return method.invoke(null, args);
    }


}
