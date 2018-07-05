package com.sapling.tools.system;

import java.util.Arrays;
import java.util.Locale;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/2
 * @since v1.0
 */
public class SystemInfoUtil {


    public static String getOS() {
        String OS_NAME = System.getProperty("os.name").toLowerCase();
        return "";
    }

    public static String currentClassPath(){
        return System.getProperty("java.class.path");
    }

    public static void main(String[] args) {
        String OS_NAME = System.getProperty("os.name").toLowerCase( );
        String OS_ARCH = System.getProperty("os.arch").toLowerCase(Locale.US);
        String OS_VERSION = System.getProperty("os.version").toLowerCase(Locale.US);

        System.out.println(OS_ARCH);
        System.out.println(OS_VERSION);

    }
}
