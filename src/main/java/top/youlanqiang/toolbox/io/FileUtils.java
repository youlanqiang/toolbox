package top.youlanqiang.toolbox.io;

import top.youlanqiang.toolbox.base.CollectionUtils;
import top.youlanqiang.toolbox.base.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import java.util.zip.Checksum;

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
     * 一些常见的视频文件后缀名
     */
    public static final List<String> VIDEO_FILE_EXT = Arrays.asList("mp4", "m4v", "mov", "avi", "flv", "mpg", "mpeg",
            "rmvb", "wmv", "asf", "dat", "asx", "wvx", "mpe", "mpa");

    /**
     * 一些常见的音频文件后缀名
     */
    public static final List<String> AUDIO_FILE_EXT = Arrays.asList("mp3", "wma", "wav", "ape", "flac", "ogg", "aac");

    /**
     * 一些常见的图片文件后缀名
     */
    public static final List<String> IMG_FILE_EXT = Arrays.asList("bmp", "jpeg", "jpg", "png", "tiff", "gif", "pcx", "tga",
            "exif", "fpx", "svg", "psd", "ico", "webp");

    /**
     * 一些常见的文档文件后缀名
     */
    public static final List<String> DOC_FILE_EXT = Arrays.asList("txt","xml", "md", "xlsx", "xls", "doc", "docx", "ppt",
            "pptx", "pdf", "odt");

    /**
     * 一些常见的压缩文件后缀名
     */
    public static final List<String> COMPRESSED_FILE_EXT = Arrays.asList("zip", "rar", "7z", "jar", "gz", "tar",  "ar",
            "bz", "car", "dar", "cpgz", "f", "ha");

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
     * 判断文件名词的后缀是否是常用的视频文件
     * @param fileName 文件名
     * @return 忽略大小写，匹配其中一项则返回true
     */
    public static boolean isVideo(String fileName){
        String fileExtension = getFileExtension(fileName);
        return StringUtils.containIgnoreCase(fileExtension, VIDEO_FILE_EXT);
    }

    /**
     * 判断文件名词的后缀是否是常用的视频文件
     * @param fileName 文件名
     * @param ext 额外进行判断的后缀名
     * @return 忽略大小写，匹配其中一项则返回true
     */
    public static boolean isVideo(String fileName, String... ext){
        if(CollectionUtils.isEmpty(ext)){
            return isVideo(fileName);
        }
        String fileExtension = getFileExtension(fileName);
        return StringUtils.containIgnoreCase(fileExtension, VIDEO_FILE_EXT) ||
                StringUtils.containIgnoreCase(fileExtension, ext);
    }

    /**
     * 判断文件名词的后缀是否是常用的图片文件
     * @param fileName 文件名
     * @return 忽略大小写，匹配其中一项则返回true
     */
    public static boolean isImg(String fileName){
        String fileExtension = getFileExtension(fileName);
        return StringUtils.containIgnoreCase(fileExtension, IMG_FILE_EXT);
    }


    /**
     * 判断文件名词的后缀是否是常用的图片文件
     * @param fileName 文件名
     * @param ext 额外进行判断的后缀名
     * @return 忽略大小写，匹配其中一项则返回true
     */
    public static boolean isImg(String fileName, String... ext){
        if(CollectionUtils.isEmpty(ext)){
            return isVideo(fileName);
        }
        String fileExtension = getFileExtension(fileName);
        return StringUtils.containIgnoreCase(fileExtension, IMG_FILE_EXT) ||
                StringUtils.containIgnoreCase(fileExtension, ext);
    }

    /**
     * 判断文件名词的后缀是否是常用的音频文件
     * @param fileName 文件名
     * @return 忽略大小写，匹配其中一项则返回true
     */
    public static boolean isAudio(String fileName){
        String fileExtension = getFileExtension(fileName);
        return StringUtils.containIgnoreCase(fileExtension, AUDIO_FILE_EXT);
    }

    /**
     * 判断文件名词的后缀是否是常用的音频文件
     * @param fileName 文件名
     * @param ext 额外进行判断的后缀名
     * @return 忽略大小写，匹配其中一项则返回true
     */
    public static boolean isAudio(String fileName, String... ext){
        if(CollectionUtils.isEmpty(ext)){
            return isVideo(fileName);
        }
        String fileExtension = getFileExtension(fileName);
        return StringUtils.containIgnoreCase(fileExtension, AUDIO_FILE_EXT) ||
                StringUtils.containIgnoreCase(fileExtension, ext);
    }

    /**
     * 判断文件名词的后缀是否是常用的压缩文件
     * @param fileName 文件名
     * @return 忽略大小写，匹配其中一项则返回true
     */
    public static boolean isCompressed(String fileName){
        String fileExtension = getFileExtension(fileName);
        return StringUtils.containIgnoreCase(fileExtension, COMPRESSED_FILE_EXT);
    }

    /**
     * 判断文件名词的后缀是否是常用的压缩文件
     * @param fileName 文件名
     * @param ext 额外进行判断的后缀名
     * @return 忽略大小写，匹配其中一项则返回true
     */
    public static boolean isCompressed(String fileName, String... ext){
        if(CollectionUtils.isEmpty(ext)){
            return isVideo(fileName);
        }
        String fileExtension = getFileExtension(fileName);
        return StringUtils.containIgnoreCase(fileExtension, COMPRESSED_FILE_EXT) ||
                StringUtils.containIgnoreCase(fileExtension, ext);
    }


    /**
     * 判断文件是否存在，不存在则直接抛出异常
     * 
     * @param file    文件
     * @param message 异常信息
     * @exception IllegalArgumentException 文件不存在异常
     */
    public static void requireExists(File file, String message) {
        Objects.requireNonNull(file, message);
        if (!file.exists()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断文件是否存在，不存在且不是一个文件直接抛出异常
     * 
     * @param file    文件
     * @param message 异常信息
     * @exception IllegalArgumentException 文件不存在异常
     */
    public static void requireFileExists(File file, String message) {
        requireExists(file, message);
        if (!file.isFile()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断文件夹是否存在，不存在且不是一个文件夹直接抛出异常
     * 
     * @param file    文件
     * @param message 异常信息
     * @exception IllegalArgumentException 文件不存在异常
     */
    public static void requireDirectoryExists(File file, String message) {
        requireExists(file, message);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 获取文件的校验和
     * 
     * @param file     文件
     * @param checksum 指定校验和算法
     * @return
     * @throws IOException
     */
    public static Checksum checksum(final File file, final Checksum checksum) throws IOException {
        requireExists(file, "file");
        if (!file.isFile()) {
            throw new IllegalArgumentException("the file param is not a file!");
        }

        Objects.requireNonNull(checksum, "checksum");
        try (InputStream inputStream = new CheckedInputStream(Files.newInputStream(file.toPath()), checksum)) {
            IOUtils.consume(inputStream);
        }
        return checksum;
    }

    /**
     * 比较2个文件内容是否一致，通过checksum进行比对
     * 
     * @param file1 文件1
     * @param file2 文件2
     * @return 文件检验和一致则返回 ture
     */
    public static boolean contentEquals(File file1, File file2) throws IOException {
        Objects.requireNonNull(file1);
        Objects.requireNonNull(file2);

        if (file1 == file2 || file1.equals(file2)) {
            return true;
        }

        if (file1.length() != file2.length()) {
            return false;
        }
        Checksum checksum1 = checksum(file1, new CRC32());
        Checksum checksum2 = checksum(file2, new CRC32());
        return checksum1.getValue() == checksum2.getValue();
    }

}
