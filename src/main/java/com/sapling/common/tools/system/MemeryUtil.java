package com.sapling.common.tools.system;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * 内存工具类，用于获取系统的内存信息（包括Jvm内存，系统内存等）
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/6/12.
 * @since v1.0
 */
public class MemeryUtil {

    /**
     * 获取总共的Jvm内存（b)
     *
     * @return Jvm内存（b)
     */
    public static long getTotalJvmMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 获取总共的Jvm内存（KB)
     *
     * @return Jvm内存（KB)
     */
    public static long getTotalKbJvmMemory() {
        return getTotalJvmMemory() / 1024;
    }

    /**
     * 获取总共的Jvm内存（MB)
     *
     * @return Jvm内存（MB)
     */
    public static long getTotalMbJvmMemory() {
        return getTotalKbJvmMemory() / 1024;
    }

    /**
     * 获取总共的Jvm内存（b)
     *
     * @return Jvm内存（b)
     */
    public static long getFreeJvmMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 获取Jvm剩余内存（Kb)
     *
     * @return Jvm剩余内存（Kb)
     */
    public static long getFreeKbJvmMemory() {
        return getFreeJvmMemory() / 1024;
    }

    /**
     * 获取Jvm剩余内存（Mb)
     *
     * @return Jvm剩余内存（Mb)
     */
    public static long getFreeMbJvmMemory() {
        return getFreeKbJvmMemory() / 1024;
    }


    /**
     * 获取总共的Jvm内存（b)
     *
     * @return Jvm内存（b)
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 获取总共的Jvm内存（Kb)
     *
     * @return Jvm内存（Kb)
     */
    public static long getMaxKbMemory() {
        return getMaxMemory() / 1024;
    }

    /**
     * 获取总共的Jvm内存（Mb)
     *
     * @return Jvm内存（Mb)
     */
    public static long getMaxMbMemory() {
        return getMaxKbMemory() / 1024;
    }

    /**
     * 获取总共的物理内存大小（b）
     *
     * @return 总共的物理内存大小（b）
     */
    public static long getTotalPhysicalMemory() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osmxb.getTotalPhysicalMemorySize();
    }

    /**
     * 获取总共的物理内存大小（Kb）
     *
     * @return 总共的物理内存大小（Kb）
     */
    public static long getTotalKbPhysicalMemory() {
        return getTotalPhysicalMemory() / 1024;
    }


    /**
     * 获取总共的物理内存大小（Mb）
     *
     * @return 总共的物理内存大小（Mb）
     */
    public static long getTotalMbPhysicalMemory() {
        return getTotalKbPhysicalMemory() / 1024;
    }


    /**
     * 获取总共的物理内存大小（b）
     *
     * @return 总共的物理内存大小（b）
     */
    public static long getFreePhysicalMemory() {
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        return osmxb.getFreePhysicalMemorySize();
    }

    /**
     * 获取总共的物理内存大小（Kb）
     *
     * @return 总共的物理内存大小（Kb）
     */
    public static long getFreeKbPhysicalMemory() {
        return getFreePhysicalMemory() / 1024;
    }

    /**
     * 获取总共的物理内存大小（Mb）
     *
     * @return 总共的物理内存大小（Mb）
     */
    public static long getFreeMbPhysicalMemory() {
        return getFreeKbPhysicalMemory() / 1024;
    }


    /**
     * 获取正在使用的Jvm内存
     *
     * @return 正在使用的Jvm内存大小
     */
    public static long getUsedJvmMemory() {
        return getTotalJvmMemory() - getFreeJvmMemory();
    }

    /**
     * 获取正在使用的Jvm内存(Kb(
     *
     * @return 正在使用的Jvm内存大小(Kb(
     */
    public static long getUsedKbJvmMemory() {
        return getUsedJvmMemory() / 1024;
    }

    /**
     * 获取正在使用的Jvm内存(Kb(
     *
     * @return 正在使用的Jvm内存大小(Kb(
     */
    public static long getUsedMbJvmMemory() {
        return getUsedKbJvmMemory() / 1024;
    }


    /**
     * 获取当前正在使用的物理内存大小
     *
     * @return 正在使用的物理内存大小
     */
    public static long getUsedPhysicalMemory() {
        return getTotalPhysicalMemory() - getFreePhysicalMemory();
    }

    /**
     * 获取当前正在使用的物理内存大小（kb）
     *
     * @return 正在使用的物理内存大小（kb）
     */
    public static long getUsedKbPhysicalMemory() {
        return getUsedPhysicalMemory() / 1024;
    }

    /**
     * 获取当前正在使用的物理内存大小（Mb）
     *
     * @return 正在使用的物理内存大小（Mb）
     */
    public static long getUsedMbPhysicalMemory() {
        return getUsedKbPhysicalMemory() / 1024;
    }

}
