package com.sapling.common.tools.file;

import com.sapling.common.tools.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * jar包操作类
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/11/9.
 * @since v1.0
 */
public class JarUtil {

    private static Logger logger = LoggerFactory.getLogger(JarUtil.class);

    /**
     * 遍历jar包，获取所有的文件信息
     *
     * @param jarFileName   jar包地址
     * @return
     */
    public static Set<String> recursionListFile(String jarFileName) {
        return recursionListFile(jarFileName,false, null);
    }

    /**
     * 遍历jar包，获取所有的文件信息
     * @param jarFileName   jar包地址
     * @param packNames     显示的包名（只显示此包下的文件）
     * @return
     */
    public static Set<String> recursionListFile(String jarFileName, String... packNames) {
        return recursionListFile(jarFileName,false, packNames);
    }

    /**
     * 遍历jar包，获取所有的文件信息
     * @param jarFileName jar包地址
     * @param lookinJar     是否显示包内的jar包中的内容
     * @param packNames     显示的包名（只显示此包下的文件）
     * @return
     */
    public static Set<String> recursionListFile(String jarFileName, boolean lookinJar, String... packNames) {
        if (!StringUtil.isEmpty(jarFileName)) {
            try {
                JarFile jarFile = new JarFile(jarFileName);
                return recursionListFile(jarFile,lookinJar, packNames);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 遍历jar包中的文件
     *
     * @param jarFile
     * @return
     */
    public static Set<String> recursionListFile(JarFile jarFile) {
        if (jarFile == null) {
            return null;
        }
        return recursionListFile(jarFile, false, null);
    }

    public static Set<String> recursionListFile(JarFile jarFile, String... packNames) {
        if (jarFile == null) {
            return null;
        }
        return recursionListFile(jarFile, false, packNames);
    }


    public static Set<String> recursionListFile(JarFile jarFile,boolean lookinJar, String... packNames) {
        Set retList = new HashSet();
        List<String> packFileNames = FileUtil.packageToUnixPath(packNames);
        if (jarFile == null) {
            return null;
        }
        Enumeration<JarEntry> entryEnumeration = jarFile.entries();
        while (entryEnumeration.hasMoreElements()) {
            JarEntry jarEntry = entryEnumeration.nextElement();
            String fileName = jarEntry.getName();
            if (lookinJar && fileName.endsWith(".jar")) {
                try {
                    InputStream inputStream = jarFile.getInputStream(jarEntry);
                    File subFile = FileUtil.createSystemTempFile(inputStream,jarEntry.getName(),".jar" );
                    JarFile subJarFile = new JarFile(subFile);
                    Set<String> subset = recursionListFile(subJarFile, lookinJar, packNames);
                    if (subset != null) {
                        retList.addAll(subset);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (packFileNames != null && packFileNames.size() > 0) {
                    for (String packFileName : packFileNames) {
                        if (fileName.startsWith(packFileName)) {
                            retList.add(FileUtil.pathToPackage(fileName));
                        }
                    }
                } else {
                    retList.add(FileUtil.pathToPackage(fileName));
                }
            }
        }
        return retList;
    }

    public static Set<Class> getClassFromPackages(JarFile jarFile,String... packageNames) throws IOException {
        Set<Class> retSet = new HashSet<>();
        Set<String> clazzNames = recursionListFile(jarFile,true, packageNames);
        for (String clazzName : clazzNames){
            if (clazzName.endsWith(".class")) {
                String fullClassName = FileUtil.pathToPackage(clazzName.substring(0,clazzName.indexOf(".class")));
                try {
                    Class clazz = Thread.currentThread().getContextClassLoader().loadClass(fullClassName);
                    retSet.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return retSet;
    }

    public static Set<Class> getClassFromPackages(String... packageNames) throws IOException {
        Set<Class> retSet = new HashSet<>();
        String jarFileName = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        logger.debug("jarFileName:{}" ,jarFileName.substring(0,jarFileName.indexOf("!")));
        String jarPath = jarFileName.substring(0,jarFileName.indexOf("!"));
        URL url = new URL(jarPath);
        logger.info(url.getPath());
        JarFile jarFile = new JarFile(url.getFile());
        return getClassFromPackages(jarFile,packageNames);
    }

    public static void main(String[] args) throws IOException {
        String jarFileName = "D:/workspace5/project/register-server/build/libs/register-server-1.0-SNAPSHOT.jar";
        System.out.println(recursionListFile(jarFileName));
    }
}
