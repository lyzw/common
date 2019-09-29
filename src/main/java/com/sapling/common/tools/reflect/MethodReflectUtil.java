package com.sapling.common.tools.reflect;

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

}
