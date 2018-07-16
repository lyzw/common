package com.sapling.common.tools.bean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/15
 * @since v1.0
 */
public class BeanUtil {

    public static Type get(Class clazz){
        ParameterizedType type = (ParameterizedType)clazz.getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        return actualTypeArguments[0];
    }
}
