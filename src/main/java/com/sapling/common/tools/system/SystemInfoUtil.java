package com.sapling.common.tools.system;

import java.util.Properties;

import sun.awt.OSInfo;

/**
 * 系统工具类
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/6/9.
 * @since v1.0
 */
public class SystemInfoUtil {
    /** Java的运行环境版本 */
    public static final String JAVA_VERSION = "java.version";
    /** Java的运行环境供应商 */
    public static final String JAVA_VENDOR = "java.vendor";
    /** Java供应商的URL */
    public static final String JAVA_VENDOR_URL = "java.vendor.url";
    /** Java的安装路径 */
    public static final String JAVA_HOME = "java.home";
    /** Java的虚拟机规范版本 */
    public static final String JAVA_VM_SPECIFICATION_VERSION = "java.vm.specification.version";
    /** Java的虚拟机规范供应商 */
    public static final String JAVA_VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";
    /** Java的虚拟机规范名称 */
    public static final String JAVA_VM_SPECIFICATION_NAME = "java.vm.specification.name";
    /** Java的虚拟机实现版本 */
    public static final String JAVA_VM_VERSION = "java.vm.version";
    /** Java的虚拟机实现供应商 */
    public static final String JAVA_VM_VENDOR = "java.vm.vendor";
    /** Java的虚拟机实现名称 */
    public static final String JAVA_VM_NAME = "java.vm.name";
    /** Java运行时环境规范版本 */
    public static final String JAVA_SPECIFICATION_VERSION = "java.specification.version";
    /** Java运行时环境规范供应商 */
    public static final String JAVA_SPECIFICATION_VENDER = "java.specification.vender";
    /** Java运行时环境规范名称 */
    public static final String JAVA_SPECIFICATION_NAME = "java.specification.name";
    /** Java的类格式版本号 */
    public static final String JAVA_CLASS_VERSION = "java.class.version";
    /** Java的类路径 */
    public static final String JAVA_CLASS_PATH = "java.class.path";
    /** 加载库时搜索的路径列表 */
    public static final String JAVA_LIBRARY_PATH = "java.library.path";
    /** 默认的临时文件路径 */
    public static final String JAVA_IO_TMPDIR = "java.io.tmpdir";
    /** 一个或多个扩展目录的路 */
    public static final String JAVA_EXT_DIRS = "java.ext.dirs";
    /** 操作系统的名称 */
    public static final String OS_NAME = "os.name";
    /** 操作系统的构架 */
    public static final String OS_ARCH = "os.arch";
    /** 操作系统的版本 */
    public static final String OS_VERSION = "os.version";
    /** 文件分隔符 */
    public static final String FILE_SEPARATOR = "file.separator";
    /** 路径分隔符 */
    public static final String PATH_SEPARATOR = "path.separator";
    /** 行分隔符 */
    public static final String LINE_SEPARATOR = "line.separator";
    /** 用户的账户名称 */
    public static final String USER_NAME = "user.name";
    /** 用户的主目录 */
    public static final String USER_HOME = "user.home";
    /** 用户的当前工作目录 */
    public static final String USER_DIR = "user.dir";


    static Properties props;

    static {
        props = System.getProperties();
    }

    /**
     * java版本号
     *
     * @return 返回java版本号
     */
    public static String getJavaVersion() {
        return props.getProperty(JAVA_VERSION);
    }

    /**
     * Java的运行环境供应商
     *
     * @return Java的运行环境供应商
     */
    public static String getJavaVendor() {
        return props.getProperty(JAVA_VENDOR);
    }

    /**
     * Java的安装路径
     *
     * @return Java的安装路径
     */
    public static String getJavaHome() {
        return props.getProperty(JAVA_HOME);
    }

    /**
     * Java的虚拟机规范版本
     *
     * @return Java的虚拟机规范版本
     */
    public static String getJavaVmSpecificationVersion() {
        return props.getProperty(JAVA_VM_SPECIFICATION_VERSION);
    }

    /**
     * Java的虚拟机规范供应商
     *
     * @return Java的虚拟机规范供应商
     */
    public static String getJavaVmSpecificationVendor() {
        return props.getProperty(JAVA_VM_SPECIFICATION_VENDOR);
    }


    /**
     * Java的虚拟机规范版本
     *
     * @return Java的虚拟机规范版本
     */
    public static String getJavaVmSpecificationName() {
        return props.getProperty(JAVA_VM_SPECIFICATION_NAME);
    }


    /**
     * Java的虚拟机实现版本
     *
     * @return Java的虚拟机实现版本
     */
    public static String getJavaVmVersion() {
        return props.getProperty(JAVA_VM_VERSION);
    }

    /**
     * 操作系统的名称
     *
     * @return 操作系统的名称
     */
    public static String getOsName() {
        return props.getProperty(OS_NAME);
    }

    /**
     * Java供应商的URL
     *
     * @return Java供应商的URL
     */
    public static String getJavaVendorUrl() {
        return props.getProperty(JAVA_VENDOR_URL);
    }

    /**
     * Java运行时环境规范名称
     *
     * @return Java运行时环境规范名称
     */
    public static String getJavaSpecificationName() {
        return props.getProperty(JAVA_SPECIFICATION_NAME);
    }

    /**
     * Java运行时环境规范供应商
     *
     * @return Java运行时环境规范供应商
     */
    public static String getJavaSpecificationVender() {
        return props.getProperty(JAVA_SPECIFICATION_VENDER);
    }

    /**
     * Java运行时环境规范版本
     *
     * @return Java运行时环境规范版本
     */
    public static String getJavaSpecificationVersion() {
        return props.getProperty(JAVA_SPECIFICATION_VERSION);
    }


    /**
     * Java运行时环境规范版本
     *
     * @return Java运行时环境规范版本
     */
    public static String getJavaClassPath() {
        return props.getProperty(JAVA_CLASS_PATH);
    }

    /**
     * Java的类格式版本号
     *
     * @return Java的类格式版本号
     */
    public static String getJavaClassVersion() {
        return props.getProperty(JAVA_CLASS_VERSION);
    }

    /**
     * 一个或多个扩展目录的路
     *
     * @return 一个或多个扩展目录的路
     */
    public static String getJavaExtDirs() {
        return props.getProperty(JAVA_EXT_DIRS);
    }

    /**
     * Java的虚拟机实现名称
     *
     * @return Java的虚拟机实现名称
     */
    public static String getJavaVmName() {
        return props.getProperty(JAVA_VM_NAME);
    }

    /**
     * 默认的临时文件路径
     *
     * @return 默认的临时文件路径
     */
    public static String getJavaIoTmpdir() {
        return props.getProperty(JAVA_IO_TMPDIR);
    }


    /**
     * 加载库时搜索的路径列表
     *
     * @return 加载库时搜索的路径列表
     */
    public static String getJavaLibraryPath() {
        return props.getProperty(JAVA_LIBRARY_PATH);
    }


    /**
     * Java的虚拟机实现供应商
     *
     * @return Java的虚拟机实现供应商
     */
    public static String getJavaVmVendor() {
        return props.getProperty(JAVA_VM_VENDOR);
    }


    /**
     * 操作系统的构架
     *
     * @return 操作系统的构架
     */
    public static String getOsArch() {
        return props.getProperty(OS_ARCH);
    }

    /**
     * 操作系统的版本
     *
     * @return 操作系统的版本
     */
    public static String getOsVersion() {
        return props.getProperty(OS_VERSION);
    }


    /**
     * 路径分隔符
     *
     * @return 路径分隔符
     */
    public static String getPathSeparator() {
        return props.getProperty(PATH_SEPARATOR);
    }

    /**
     * 文件分隔符
     *
     * @return 文件分隔符
     */
    public static String getFileSeparator() {
        return props.getProperty(FILE_SEPARATOR);
    }

    /**
     * 用户的当前工作目录
     *
     * @return 用户的当前工作目录
     */
    public static String getUserDir() {
        return props.getProperty(USER_DIR);
    }

    /**
     * 用户的主目录
     *
     * @return 用户的主目录
     */
    public static String getUserHome() {
        return props.getProperty(USER_HOME);
    }

    /**
     * 用户的账户名称
     *
     * @return 用户的账户名称
     */
    public static String getUserName() {
        return props.getProperty(USER_NAME);
    }

    /**
     * 用户的账户名称
     *
     * @return 用户的账户名称
     */
    public static String getLineSeparator() {
        return props.getProperty(LINE_SEPARATOR);
    }


    /**
     * 获取某种系统信息
     *
     * @param type 类型
     * @return 系统信息
     */
    public static String getSysInfo(String type) {
        return props.getProperty(type);
    }



    public static String getEnvProSeperator(){
        if (OSInfo.getOSType().equals(OSInfo.OSType.WINDOWS)){
            return ";";
        }else {
            return ":";
        }
    }
}
