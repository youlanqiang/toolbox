package top.youlanqiang.toolbox.base;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * IO工具类
 * 
 * @author youlanqiang
 *         created in 2023/01/11 20:20
 * 
 */
public final class IOHepler {

    /**
     * 执行close操作，并忽略掉可能发生的异常
     * 
     * @param closeable closeable对象,例如InputStream对象
     */
    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            // ignore exception
        }
    }

    /**
     * 读取resources文件夹下文件流
     * 
     * @param filePath 相对路径
     * @return 文件流
     */
    public static InputStream getResourceAsStream(String filePath) {

        return IOHepler.class.getClassLoader().getResourceAsStream(filePath);
    }

    /**
     * 获取resources下文件流并转换为字符串返回
     * 
     * @param filePath 相对文件路径
     * @return UTF8字符串,读取不到会返回null
     */
    public static String getResourceAsStr(String filePath) {
        try {
            return getResourceAsStrWithThrows(filePath, StandardCharsets.UTF_8);
        } catch (IOException exception) {
            // ignore exception
            return null;
        }
    }

    /**
     * 获取resources下文件流并转换为字符串返回
     * 
     * @param filePath 相对文件路径
     * @param charset  字符串编码
     * @return 字符串,读取不到会返回null
     */
    public static String getResourceAsStr(String filePath, Charset charset) {
        try {
            return getResourceAsStrWithThrows(filePath, charset);
        } catch (IOException exception) {
            // ignore exception
            return null;
        }
    }

    /**
     * 获取resources下文件流并转换为字符串返回,该操作会抛异常
     * 
     * @param filePath 相对文件路径
     * @return UTF8编码文件
     * @throws IOException 字符串
     */
    public static String getResourceAsStrWithThrows(String filePath) throws IOException {
        return getStrAndCloseWithThrows(getResourceAsStream(filePath), StandardCharsets.UTF_8);
    }

    /**
     * 获取resources下文件流并转换为字符串返回,该操作会抛异常
     * 
     * @param filePath 相对文件路径
     * @param charset  字符串编码
     * @return 字符串
     * @throws IOException IO异常
     */
    public static String getResourceAsStrWithThrows(String filePath, Charset charset) throws IOException {
        return getStrAndCloseWithThrows(getResourceAsStream(filePath), charset);
    }

    /**
     * 获取stream字符串并关闭stream
     * 
     * @param in inputstream
     * @return UTF8字符串,读取不到会返回null
     */
    public static String getStrAndClose(InputStream in) {
        return getStrAndClose(in, StandardCharsets.UTF_8);
    }

    /**
     * 获取stream字符串并关闭stream
     * 
     * @param charset 字符串编码
     * @param in      inputstream
     * @return 字符串,读取不到会返回null
     */
    public static String getStrAndClose(InputStream in, Charset charset) {
        try {
            return getStrAndCloseWithThrows(in, charset);
        } catch (IOException exception) {
            // ignore exception
            return null;
        }
    }

    /**
     * 读取stream流下的byte数组转换为字符串返回，最后会关闭stream，该操作会抛异常
     * 
     * @param in stream流
     * @return UTF8编码字符串
     * @throws IOException IO异常
     */
    public static String getStrAndCloseWithThrows(InputStream in) throws IOException {
        return getStrAndCloseWithThrows(in, StandardCharsets.UTF_8);
    }

    /**
     * 读取stream流下的byte数组转换为字符串返回，最后会关闭stream，该操作会抛异常
     * 
     * @param in      stream流
     * @param charset 字符串编码
     * @return 字符串
     * @throws IOException IO异常
     */
    public static String getStrAndCloseWithThrows(InputStream in, Charset charset) throws IOException {
        String result = getStrWithThrows(in, charset);
        close(in);
        return result;
    }

    /**
     * 读取stream流下的byte数组转换为字符串返回，该操作会抛异常
     * 
     * @param in stream流
     * @return UTF8字符串
     * @throws IOException IO异常
     */
    public static String getStrWithThrows(InputStream in) throws IOException {
        return getStrWithThrows(in, StandardCharsets.UTF_8);
    }

    /**
     * 读取stream流下的byte数组转换为字符串返回，该操作会抛异常
     * 
     * @param in      stream流
     * @param charset 字符串编码
     * @return 字符串
     * @throws IOException IO异常
     */
    public static String getStrWithThrows(InputStream in, Charset charset) throws IOException {
        return new String(in.readAllBytes(), charset);
    }
}
