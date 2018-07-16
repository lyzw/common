package com.sapling.common.tools.file;

import com.sapling.common.tools.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 文件搜索工具类
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/11/13.
 * @since v1.0
 */
public class FileSearchUtil {

    private static Logger logger = LoggerFactory.getLogger(FileSearchUtil.class);


    /**
     * 找出文件下的所有的某些类型的文件
     *
     * @param parentPath
     * @param isRetain   过滤类型，true为只保留指定类型，false为剔除指定类型
     * @param types
     * @return
     */
    public static Set<String> findFilesInPath(String parentPath, Boolean isRetain, final String[] types) {
        if (StringUtil.isEmpty(parentPath)) {
            return Collections.emptySet();
        }
        Set<String> retSet = new HashSet<>();
        File file = new File(parentPath);
        if (file.isFile()) {
            if (isRetain && checkIsInFileTypes(file, types)) {
                retSet.add(file.getAbsolutePath());
                return retSet;
            }
            return Collections.emptySet();
        }
        File[] files = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isDirectory()) {
                    return true;
                }
                boolean flag = false;
                for (String type : types) {
                    if (pathname.getName().endsWith(type)) {
                        flag = true;
                        break;
                    }
                }
                return isRetain && flag;
            }
        });
        for (File childFile : files) {
            if (childFile.isDirectory()) {
                Set subSet = findFilesInPath(childFile.getAbsolutePath(), isRetain, types);
                if (subSet != null) {
                    retSet.addAll(subSet);
                }
            } else {
                retSet.add(childFile.getAbsolutePath());
            }
        }
        return retSet;
    }


    /**
     * 判断指定的文件是否是某种类型的文件
     *
     * @param file
     * @param type
     * @return
     */
    public static boolean checkIsFileType(File file, String type) {
        if (file.isDirectory()) {
            return false;
        }
        if (file.getName().endsWith(type)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断文件类型是否在指定的范围内
     *
     * @param file
     * @param types
     * @return
     */
    public static boolean checkIsInFileTypes(File file, String[] types) {
        if (file.isDirectory()) {
            return false;
        }
        for (String type : types) {
            if (file.getName().endsWith(type)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 从给定的路径中获取所有的class文件
     *
     * @param fileName
     * @return
     */
    public static Set<Class> findClassesInPath(String fileName) {
        return findClassesInPath(fileName, null);
    }

    /**
     * 从给定的路径中获取所有的class文件
     *
     * @param fileName
     * @return
     */
    public static Set<Class> findClassesInPath(String fileName, String... packageNames) {
        File file = new File(fileName);
        Set<Class> classSet = new HashSet<>();
        Set<String> classfiles = findFilesInPath(fileName, true, new String[]{".class"});
        for (String classfile : classfiles) {
            String parentPath = file.getAbsolutePath();
            if (!parentPath.endsWith(File.separator)) {
                parentPath = parentPath + File.separator;
            }

            String className = FileUtil.pathToPackage(classfile.replace(parentPath, ""));
            try {
                Class clazz = null;
                if (packageNames != null && packageNames.length > 0) {
                    for (String packageName : packageNames) {
                        if (className.startsWith(packageName)) {
                            clazz = Thread.currentThread().getContextClassLoader().loadClass(
                                    className.substring(0, className.indexOf(".class")));
                            break;
                        }
                    }
                } else {
                    clazz = Thread.currentThread().getContextClassLoader().loadClass(
                            className.substring(0, className.indexOf(".class")));
                }
                classSet.add(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return classSet;
    }

}
