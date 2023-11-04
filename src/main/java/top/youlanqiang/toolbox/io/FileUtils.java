package top.youlanqiang.toolbox.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Objects;

/**
 * File对象方法，这个工具类是对{@link java.nio.file.Files}和{@link java.nio.file.Paths}的拓展
 */
public class FileUtils {

    /**
     * 常用文件byte大小
     */
    public static final String BYTE = "byte";

    public static final String BYTES = "bytes";

    public static final long ONE_KB = 1024;

    public static final BigInteger ONE_KB_BI = BigInteger.valueOf(ONE_KB);

    public static final String KB = "KB";

    public static final long ONE_MB = ONE_KB * ONE_KB;

    public static final BigInteger ONE_MB_BI = BigInteger.valueOf(ONE_MB);

    public static final String MB = "MB";

    public static final long ONE_GB = ONE_KB * ONE_MB;

    public static final BigInteger ONE_GB_BI = BigInteger.valueOf(ONE_GB);

    public static final String GB = "GB";

    public static final long ONE_TB = ONE_KB * ONE_GB;

    public static final BigInteger ONE_TB_BI = BigInteger.valueOf(ONE_TB);

    public static final String TB = "TB";

    /**
     * @see #byteCountToDisplaySize(BigInteger)
     */
    public static String byteCountToDisplaySize(Number fileSize) {
        return byteCountToDisplaySize(fileSize.longValue());
    }

    /**
     * @see #byteCountToDisplaySize(BigInteger)
     */
    public static String byteCountToDisplaySize(long fileSize) {
        return byteCountToDisplaySize(BigInteger.valueOf(fileSize));
    }

    /**
     * bytes字节数转为可读的文件大小,如果传入的数是负数或0则只会返回0byte
     * 如： 1024 => 1MB
     * 
     * @param bytesSize bytes字节数
     * @return 可读字符串
     */
    public static String byteCountToDisplaySize(final BigInteger bytesSize) {
        Objects.requireNonNull(bytesSize);
        if (bytesSize.compareTo(BigInteger.ZERO) < 1) {
            return "0" + BYTE;
        }

        StringBuffer display = new StringBuffer();
        BigInteger tempFileSize = BigInteger.valueOf(bytesSize.longValue());

        if (tempFileSize.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0) {
            display.append(tempFileSize.divide(ONE_TB_BI)).append(TB);
            tempFileSize = tempFileSize.remainder(ONE_TB_BI);
        }

        if (tempFileSize.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0) {
            display.append(tempFileSize.divide(ONE_GB_BI)).append(GB);
            tempFileSize = tempFileSize.remainder(ONE_GB_BI);
        }

        if (tempFileSize.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0) {
            display.append(tempFileSize.divide(ONE_MB_BI)).append(MB);
            tempFileSize = tempFileSize.remainder(ONE_MB_BI);
        }

        if (tempFileSize.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0) {
            display.append(tempFileSize.divide(ONE_KB_BI)).append(KB);
            tempFileSize = tempFileSize.remainder(ONE_KB_BI);
        }

        if (tempFileSize.compareTo(BigInteger.ZERO) > 0) {
            display.append(tempFileSize).append(BYTES);

        }
        return display.toString();
    }

    /**
     * 获取文件名称
     * 如 /root/hello.txt => hello.txt
     * 
     * @param fullName 全路径文件名称
     * @return 文件名称
     */
    public static String getName(String fullName) {
        Objects.requireNonNull(fullName);
        return new File(fullName).getName();
    }

    /**
     * 获取文件的后缀
     * 如： hello.txt => txt
     * /root/hello.txt => txt
     * 
     * @param fullName 全路径文件名称
     * @return 文件后缀名称
     */
    public static String getFileExtension(String fullName) {
        String fileName = getName(fullName);
        int dotIndex = fileName.lastIndexOf(".");
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }

    /**
     * 获取文件名称，不包含文件的后缀
     * 如： hello.txt => hello
     * /root/hello.txt => hello
     * 
     * @param fullName 全路径文件名称
     * @return 文件名称不包含文件后缀
     */
    public static String getNameWithoutExtension(String fullName) {
        String fileName = getName(fullName);
        int dotIndex = fileName.lastIndexOf(".");
        return dotIndex == -1 ? fileName : fileName.substring(0, dotIndex);
    }

    /**
     * 判断文件是否存在，不存在则直接抛出异常
     * 
     * @param file    文件
     * @param message 异常信息
     * @exception IlleaglArgumentException 文件不存在异常
     */
    public static void requireExists(File file, String message) {
        Objects.requireNonNull(file, message);
        if (!file.exists()) {
            throw new IllegalArgumentException(message);
        }
    }

    private FileUtils() {
    }

}
