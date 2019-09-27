package com.sapling.common.tools.system;

import java.math.BigDecimal;
import java.util.*;

/**
 * {description here}
 *
 * @author wei.zhou
 * @date 2019/9/26
 * @since 1.0
 */
public enum DeclaredClassNameEnum {

    /**
     * java type : java.lang.String
     */
    LANG_STRING(String.class.getName(), String.class.getCanonicalName()),
    /**
     * java type : java.lang.Byte
     */
    LANG_BYTE(Byte.class.getName(), Byte.class.getCanonicalName()),
    /**
     * java type : java.lang.Boolean
     */
    LANG_BOOLEAN(Boolean.class.getName(), Boolean.class.getCanonicalName()),
    /**
     * java type : java.lang.String
     */
    LANG_INTEGER(Integer.class.getName(), Integer.class.getName()),
    /**
     * java type : java.lang.String
     */
    LANG_SHORT(Short.class.getName(), Short.class.getName()),
    /**
     * java type : java.lang.String
     */
    LANG_LONG(Long.class.getName(), Long.class.getName()),
    /**
     * java type : java.lang.String
     */
    LANG_FLOAT(Float.class.getName(), Float.class.getName()),
    /**
     * java type : java.lang.String
     */
    LANG_DOUBLE(Double.class.getName(), Double.class.getName()),
    /**
     * java native type : int
     */

    NATIVE_BYTE("int", "int"),
    /**
     * java native type : int
     */
    NATIVE_BOOLEAN("boolean", "boolean"),
    /**
     * java native type : int
     */
    NATIVE_INTEGER("int", "int"),
    /**
     * java native type : int
     */
    NATIVE_SHORT("short", "short"),
    /**
     * java native type : int
     */
    NATIVE_LONG("long", "long"),
    /**
     * java native type : int
     */
    NATIVE_FLOAT("float", "float"),
    /**
     * java native type : int
     */
    NATIVE_DOUBLE("double", "double"),

    /**
     * java type : java.math.BigDecimal
     */
    MATH_BIG_DECIMAL(BigDecimal.class.getName(), BigDecimal.class.getName()),
    /**
     * java type : java.util.Date
     */
    UTIL_DATE(Date.class.getName(), Date.class.getName()),
    /**
     * java type : java.util.Map
     */
    UTIL_MAP(Map.class.getName(), Map.class.getName()),
    /**
     * java type : java.util.List
     */
    UTIL_LIST(List.class.getName(), List.class.getName()),
    /**
     * java type : java.util.ArrayList
     */
    UTIL_ARRAY_LIST(ArrayList.class.getName(), ArrayList.class.getName()),
    /**
     * java type : java.util.LinkedList
     */
    UTIL_LINKED_LIST(LinkedList.class.getName(), LinkedList.class.getName()),
    ;
    private String name;
    private String canonicalName;

    DeclaredClassNameEnum(String name, String canonicalName) {
        this.name = name;
        this.canonicalName = canonicalName;
    }
}
