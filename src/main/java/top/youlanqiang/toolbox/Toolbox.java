package top.youlanqiang.toolbox;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

import top.youlanqiang.toolbox.base.ObjectHepler;
import top.youlanqiang.toolbox.io.IOHepler;
import top.youlanqiang.toolbox.text.StringHepler;

/**
 * 工具类常用方法的集合
 * 
 * @author youlanqiang
 *         created in 2022/12/03 09:30
 * 
 */
public final class Toolbox {

    private Toolbox() {
    }

    /**
     * 快速打印格式化字符串内容
     * 
     * @param pattern 格式化字符串
     * @param args    参数列表
     */
    public static void println(String pattern, Object... args) {
        System.out.println(format(pattern, args));
    }

    /**
     * 使用默认的占位符 {} 格式化字符串内容
     * 
     * @param text 字符串模版
     * @param args 参数列表
     * @return 格式化后的格式化字符串
     */
    public static String format(String text, Object... args) {
        return StringHepler.format(text, args);
    }

    /**
     * 判断对象是否为null，或空
     * 
     * @param obj 对象
     * @return true or false
     */
    public static boolean isEmpty(Object obj) {
        return ObjectHepler.isEmpty(obj);
    }

    /**
     * 数组是否为空
     * 
     * @param objects 数组
     * @return true or false
     */
    public static boolean isEmpty(Object... objects) {
        return ObjectHepler.isEmpty(objects);
    }

    /**
     * Map是否为空
     * 
     * @param map map对象
     * @return true or false
     */
    public static boolean isEmpty(Map<Object, Object> map) {
        return ObjectHepler.isEmpty(map);
    }

    /**
     * 集合是否为空
     * 
     * @param collection 集合对象
     * @return true or false
     */
    public static boolean isEmpty(Collection<Object> collection) {
        return ObjectHepler.isEmpty(collection);
    }

    /**
     * result为false，则抛出自定义的exception
     * 
     * @param <X>               自定义异常
     * @param result            断言结果
     * @param exceptionSupplier 自定义异常
     * @throws X 自定义的异常
     */
    public static <X extends Throwable> void assertBoolean(boolean result,
            Supplier<? extends X> exceptionSupplier) throws X {
        if (!result) {
            throw exceptionSupplier.get();
        }
    }

    /**
     * 读取resources文件夹下文件流
     * 
     * @param filePath 相对路径
     * @return 文件流
     */
    public static InputStream getResourceAsStream(String filePath) {
        return IOHepler.getResourceAsStream(filePath);
    }

}
