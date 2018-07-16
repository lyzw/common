package com.sapling.common.tools.system;

import java.lang.management.ManagementFactory;

/**
 * //TODO {Please input description here }
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/11/6.
 * @since v1.0
 */
public class MXBeanUtil {

    public static Integer getPid(){
        return Integer.parseInt(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
    }

    public static void main(String[] args) {
        System.out.println(MXBeanUtil.getPid());
    }
}
