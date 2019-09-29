package com.sapling.common.tools.reflect;

import com.sapling.common.tools.collection.ArrayUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * method util
 *
 * @author wei.zhou
 * @date 2019/9/25
 * @since 1.0
 */
public class MethodReflectUtil {

    /**
     * Getting the list of method with the same name
     *
     * @param clazz      Class
     * @param methodName method name
     * @return list of method with the same name
     */
    public static List<Method> getMethods(Class clazz, String methodName) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> methodName.equals(method.getName()))
                .collect(Collectors.toList());
    }

    /**
     * get the method with parameter types
     *
     * @param clazz          class of the method belongs to
     * @param methodName     method name
     * @param parameterTypes parameter type of the method
     * @return method
     * @throws NoSuchMethodException
     */
    public static Method getMethod(Class clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        return clazz.getMethod(methodName, parameterTypes);
    }

    /**
     * get the method without parameter
     *
     * @param clazz      class which method belongs to
     * @param methodName method name
     * @return method
     * @throws NoSuchMethodException
     */
    public static Method getMethod(Class clazz, String methodName) throws NoSuchMethodException {
        return getMethod(clazz, methodName);
    }

    /**
     * get static method with method name and  parameter types
     *
     * @param clazz          class which method belongs to
     * @param methodName     method name
     * @param parameterTypes parameter type of the method
     * @return
     * @throws NoSuchMethodException
     */
    public static Method getStaticMethod(Class clazz, String methodName, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = getMethod(clazz, methodName, parameterTypes);
        if (Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        return null;
    }

    /**
     * Get static method from the specified class
     *
     * @param clazz      class which method belongs to
     * @param methodName method name
     * @return
     * @throws NoSuchMethodException
     */
    public static Method getStaticMethod(Class clazz, String methodName) throws NoSuchMethodException {
        Method method = getMethod(clazz, methodName);
        if (Modifier.isStatic(method.getModifiers())) {
            return method;
        }
        return null;
    }

    /**
     * get list of the static methods
     *
     * @param methodName method name
     * @param clazz
     * @return
     */
    public static List<Method> getStaticMethods(String methodName, Class clazz) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> methodName.equals(method.getName()) && Modifier.isStatic(method.getModifiers()))
                .collect(Collectors.toList());
    }

    public static Object invokeMethodWithNoParameters(String methodName, Object object) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getMethod(object.getClass(), methodName);
        if (method != null) {
            method.setAccessible(true);
            return method.invoke(object);
        }
        return null;
    }

    /**
     * Invokes the underlying method represented by this {@code Method} object, on the specified object with the specified parameters.
     *
     * @param methodName the name of the method which wanted to invoke
     * @param object     the specified object
     * @param parameters the specified parameters to invoke the method
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object invokeMethod(String methodName, Object object, Object... parameters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = null;
        if (parameters != null && parameters.length > 0) {
            List<Class> parameterTypes = Arrays.stream(parameters).map(Object::getClass).collect(Collectors.toList());
            Class[] parameterTypeArray = new Class[parameters.length];
            parameterTypeArray = parameterTypes.toArray(parameterTypeArray);
            method = getMethod(object.getClass(), methodName, parameterTypeArray);
            if (method != null) {
                return method.invoke(object, parameters);
            }
        } else {
            method = getMethod(object.getClass(), methodName);
            if (method != null) {
                return method.invoke(object);
            }
        }
        return null;
    }

    /**
     * Invokes the static method represented by this {@code Method} object, on the specified object with the specified parameters.
     *
     * @param methodName the name of the method which wanted to invoke
     * @param clazz      the class which the method belongs to
     * @param parameters the specified parameters to invoke the method
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object invokeStaticMethod(String methodName, Class clazz, Object... parameters) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = null;
        if (parameters != null && parameters.length > 0) {
            List<Class> parameterTypes = Arrays.stream(parameters).map(Object::getClass).collect(Collectors.toList());
            Class[] parameterTypeArray = new Class[parameters.length];
            parameterTypeArray = parameterTypes.toArray(parameterTypeArray);
            method = getStaticMethod(clazz, methodName, parameterTypeArray);
            if (method != null) {
                method.setAccessible(true);
                return method.invoke(null, parameters);
            }
        } else {
            method = getMethod(clazz, methodName);
            if (method != null) {
                method.setAccessible(true);
                return method.invoke(null);
            }
        }
        return null;
    }

}
