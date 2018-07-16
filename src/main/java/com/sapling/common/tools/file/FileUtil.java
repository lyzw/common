package com.sapling.common.tools.file;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.sapling.common.tools.common.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * //TODO {Please input description here }
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/9/6.
 * @since v1.0
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /** unix格式文件分隔符 **/
    public static final String FILE_SEPERATOR_UNIX = "/";
    /** dos格式文件分隔符 **/
    public static final String FILE_SEPERATOR_DOS = "\\\\";


    /**
     * 根据给定的文件名或者路径获取其父目录
     *
     * @param src
     * @param fileSeperator
     * @return
     */
    public static String getParentDir(String src, String fileSeperator) {
        if (StringUtil.isEmpty(src)) {
            return "";
        }
        int index = 0;
        if (src.endsWith(fileSeperator)) {
            index = src.substring(0, src.length() - 1).lastIndexOf(fileSeperator);
        } else {
            index = src.lastIndexOf(fileSeperator);
        }
        return src.substring(0, index + 1);
    }

    /**
     * 根据全路径名获得文件名
     *
     * @param src
     * @param fileSeperator
     * @return
     */
    public static String getFileNameFromPath(String src, String fileSeperator) {
        if (src.endsWith(fileSeperator)) {
            return "";
        } else {
            return src.substring(src.lastIndexOf(fileSeperator) + 1);
        }

    }


    /**
     * 创建临时文件
     *
     * @param inputStream
     * @param prefix
     * @param suffix
     * @return
     */
    public static File createSystemTempFile(InputStream inputStream, String prefix, String suffix) {

        File tmpFile = null;
        FileOutputStream fileOutputStream = null;

        try {
            tmpFile = File.createTempFile(prefix, suffix);
            byte[] bytes = new byte[1024];
            fileOutputStream = new FileOutputStream(tmpFile);
            while (inputStream.read(bytes) != -1) {
                fileOutputStream.write(bytes);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {

            }
        }
        return tmpFile;
    }

    /**
     * 根据给定的输入流创建文件
     *
     * @param fileName    文件名（全路径）
     * @param inputStream 输入流
     * @return 创建的文件
     */
    public static File createFile(String fileName, InputStream inputStream) {
        return createFile(fileName,inputStream,true);
    }

    /**
     * 根据给定的输入流创建文件
     *
     * @param fileName    文件名（全路径）
     * @param inputStream 输入流
     * @return 创建的文件
     */
    public static File createFile(String fileName, InputStream inputStream,boolean isDelete) {
        File tmpFile = null;
        FileOutputStream fileOutputStream = null;
        try {
            tmpFile = new File(fileName);
            if (tmpFile.exists() && isDelete){
                tmpFile.delete();
            }
            if (!tmpFile.getParentFile().exists()) {
                tmpFile.mkdirs();
            }
            if (!tmpFile.exists()) {
                tmpFile.createNewFile();
            }

            byte[] bytes = new byte[1024];
            int length = 0;
            tmpFile.setWritable(true);
            fileOutputStream = new FileOutputStream(tmpFile);
            while ((length = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
            }
        }
        return tmpFile;
    }


    /**
     * 包名修改为路径
     *
     * @param packageName
     * @return
     */
    public static String packageToPath(String packageName) {
        return packageToPath(packageName, File.separator);
    }

    public static String packageToUnixPath(String packageName) {
        return packageToPath(packageName, FILE_SEPERATOR_UNIX);
    }

    public static String packageToDosPath(String packageName) {
        return packageToPath(packageName, FILE_SEPERATOR_DOS);
    }

    /**
     * 包名修改为路径
     *
     * @param packageName
     * @return
     */
    public static String packageToPath(String packageName, String separator) {
        if (StringUtil.isEmpty(packageName)) {
            return null;
        }
        if (packageName.indexOf(".") == -1) {
            return packageName;
        }
        return packageName.replace(".", separator);
    }

    /**
     * 包名修改为路径
     *
     * @param packageNames
     * @return
     */
    public static List<String> packageToPath(String... packageNames) {
        if (packageNames == null || packageNames.length == 0) {
            return null;
        }
        List pathes = new ArrayList();
        for (String packageName : packageNames) {
            String path = packageToPath(packageName);
            if (!StringUtil.isEmpty(path)) {
                pathes.add(path);
            }
        }
        return pathes;
    }

    /**
     * 包名修改为路径
     *
     * @param packageNames
     * @return
     */
    public static List<String> packageToUnixPath(String... packageNames) {
        if (packageNames == null || packageNames.length == 0) {
            return null;
        }
        List pathes = new ArrayList();
        for (String packageName : packageNames) {
            String path = packageToUnixPath(packageName);
            if (!StringUtil.isEmpty(path)) {
                pathes.add(path);
            }
        }
        return pathes;
    }

    /**
     * 路径名转化为包名
     *
     * @param pathName
     * @return
     */
    public static String pathToPackage(String pathName) {
        if (StringUtil.isEmpty(pathName)) {
            return null;
        }
        if (pathName.indexOf(File.separator) == -1 && pathName.indexOf(FileUtil.FILE_SEPERATOR_UNIX) == -1) {
            return pathName;
        }
        return pathName.replace(File.separator, ".").replace(FileUtil.FILE_SEPERATOR_UNIX, ".");
    }

    /**
     * 路径名转化为包名
     *
     * @param pathNames
     * @return
     */
    public static List<String> pathToPackage(String... pathNames) {
        if (pathNames == null || pathNames.length == 0) {
            return null;
        }
        List<String> packages = new ArrayList<>();
        for (String pathName : pathNames) {
            String packageName = pathToPackage(pathName);
            if (!StringUtil.isEmpty(packageName)) {
                packages.add(packageName);
            }
        }
        return packages;
    }


    public static void writeFile(File file,byte[] bytes)  throws IOException{
        FileOutputStream fileWriter = null;
        try{
            if (file.exists()){
                file.delete();
                file.createNewFile();
            }
            fileWriter = new FileOutputStream(file);
            fileWriter.write(bytes);
            fileWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            throw e;
        }

    }

}
