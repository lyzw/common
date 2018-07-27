package com.sapling.common.tools.system;


import com.sapling.common.tools.common.StringUtil;

/**
 * //TODO {Please input description here }
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/11/12.
 * @since v1.0
 */
public class ClassUtil {

    public static Class loadClass(String className) throws ClassNotFoundException {
        if (StringUtil.isEmpty(className)){
            throw new IllegalArgumentException("class name must be specified");
        }
        try {
            Class clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
            return clazz;
        }catch (ClassNotFoundException e){
            throw e;
        }
    }
}
