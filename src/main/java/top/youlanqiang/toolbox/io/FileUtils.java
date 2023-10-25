package top.youlanqiang.toolbox.io;

import static top.youlanqiang.toolbox.base.Asserts.*;

import java.io.File;
import java.nio.file.Paths;

/**
 * File对象方法，这个工具类是对{@link java.nio.file.Files}和{@link java.nio.file.Paths}的拓展
 */
public class FileUtils {

    /**
     * 获取文件的后缀
     * 如： hello.txt => txt
     * /root/hello.txt => txt
     * 
     * @param fullName 文件名称
     * @return 文件后缀名称
     */
    public static String getFileExtension(String fullName) {
        notNull(fullName);
        String fileName = new File(fullName).getName();
        int dotIndex = fileName.lastIndexOf(".");
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }

    public static String getNameWithoutExtension(String fullName) {
        return null;
    }
}
