package com.sapling.common.tools.collection;


import com.sapling.common.tools.common.ReflectUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/7
 * @since v1.0
 */
public class MapUtil {

    /**
     * 将对象的属性与值转换为map
     *
     * @param object 对象数据
     * @return 对象数据的map
     */
    public static Map objectToMap(Object object) {
        return ReflectUtil.getFieldValueMap(object);
    }

    /**
     * 将对象的属性与值转换为map
     *
     * @param object           对象数据
     * @param includeGetMethod 包含get方法
     * @return 对象数据的map
     */
    public static Map objectToMap(Object object, boolean includeGetMethod) {
        return ReflectUtil.getFieldValueMap(object);
    }

    /**
     * 将map按照k-v，实例化一个实例对象
     *
     * @param clazz 对象类型
     * @param map   名称-值map对象
     * @return 对象实例
     * @throws InstantiationException 实例化异常
     * @throws IllegalAccessException 访问异常
     */
    public static Object mapToObject(Class clazz, Map<String, Object> map)
            throws InstantiationException, IllegalAccessException {
        return ReflectUtil.getInstance(clazz, map);
    }


    /**
     * 比较两个map的数据是否相同
     *
     * @param one
     * @param two
     * @return
     */
    public static boolean compare(Map<Object, Object> one, Map<Object, Object> two) {
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        }
        if (one.size() != two.size()) {
            return false;
        }
        for (Map.Entry<Object, Object> entry : one.entrySet()) {
            Object key = entry.getKey();
            if (!two.containsKey(key)) {
                return false;
            }
            Object value = two.get(key);
            Class clazz = value.getClass();
            if (clazz.isInstance(entry.getValue())) {
                //能否所有的都用equals？
                if (value.equals(entry.getValue())) {
                    continue;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取map中的值，如果没有返回默认值
     *
     * @param map          map
     * @param key          key
     * @param defaultValue 默认值
     * @return value
     */
    public static Object getOrElse(Map<Object, Object> map, String key, Object defaultValue) {
        if (map == null || map.size() == 0) {
            return defaultValue;
        }
        if (!map.containsKey(key)) {
            return defaultValue;
        }
        return map.get(key);
    }



    public static class MapWrapper<K, V> {

        Map<K, V> owner;

        public MapWrapper(Map<K, V> owner) {
            this.owner = owner;
        }

        public MapWrapper put(K key, V value) {
            owner.put(key, value);
            return this;
        }

        public Map<K, V> owner() {
            return owner;
        }

        public Map<K, V> get() {
            return owner;
        }


    }

}
