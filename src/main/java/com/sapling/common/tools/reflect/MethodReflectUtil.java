package com.sapling.common.tools.reflect;

import java.lang.reflect.Method;
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
}
