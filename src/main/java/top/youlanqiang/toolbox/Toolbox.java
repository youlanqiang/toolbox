package top.youlanqiang.toolbox;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import top.youlanqiang.toolbox.base.ChinaAreaHepler;
import top.youlanqiang.toolbox.base.EqualsHepler;
import top.youlanqiang.toolbox.base.IOHepler;
import top.youlanqiang.toolbox.base.ObjectHepler;
import top.youlanqiang.toolbox.base.StringHepler;
import top.youlanqiang.toolbox.base.ChinaAreaHepler.ChinaAreaCode;
import top.youlanqiang.toolbox.base.ObjectHepler.ObjectCastHepler;
import top.youlanqiang.toolbox.collection.Pair;
import top.youlanqiang.toolbox.collection.Triple;
import top.youlanqiang.toolbox.concurrent.ThreadHepler;
import top.youlanqiang.toolbox.properties.PropertiesResource;

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
     * 根据区域代码获取区域信息
     * 
     * @param code 区域代码 如:330000
     * @return 区域详细信息，包含区域名称等信息
     */
    public static ChinaAreaCode getAreaByCode(String code) {
        return ChinaAreaHepler.getAreaByCode(code);
    }

    /**
     * 创建一个不可变Triple对象
     * 
     * @param <L>    左值泛型
     * @param <M>    中值泛型
     * @param <R>    右值泛型
     * @param left   左值
     * @param middle 中值
     * @param right  右值
     * @return 不可变Triple对象
     */
    public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right) {
        return Triple.of(left, middle, right);
    }

    /**
     * 创建一个不可变的Pair对象
     * 
     * @param <L>   左值泛型
     * @param <R>   右值泛型
     * @param left  左值
     * @param right 右值
     * @return ImmutablePair
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return Pair.of(left, right);
    }

    /**
     * 创建一个等值比较器
     * 
     * @return 等值比较器
     */
    public static EqualsHepler equalsHepler() {
        return new EqualsHepler();
    }

    /**
     * 快速打印
     * 
     * @param str 打印内容
     */
    public static void println(String str) {
        System.out.println(str);
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
     * 去掉头尾指定的字符串
     * 
     * @param str      字符串
     * @param splitter 指定字符串
     * @return 去掉头尾中包含指定字符串后的字符串
     */
    public static String trimBothEndsChars(String str, String splitter) {
        return StringHepler.trimBothEndsChars(str, splitter);
    }

    /**
     * 使用默认的占位符 {} 格式化字符串内容
     * 
     * @param pattern 字符串模版
     * @param args    参数列表
     * @return 格式化后的格式化字符串
     */
    public static String format(String pattern, Object... args) {
        return StringHepler.format(pattern, args);
    }

    /**
     * 创建内部类ObjectToStringBuilder，传入obj对象会将对象class的simpleName设置为默认名称
     * 
     * @param obj 构造器的默认对象
     * @return 对象字符串构造器
     */
    public static StringHepler.ObjectToStringBuilder toString(Object obj) {
        return StringHepler.build(obj);
    }

    /**
     * 创建内部类ObjectToStringBuilder，传入class名称
     * 
     * @param className 类型的名称
     * @return 对象字符串构造器
     */
    public static StringHepler.ObjectToStringBuilder toString(String className) {
        return StringHepler.build(className);
    }

    /**
     * 创建内部类ObjectToStringBuilder，传入class的类名
     * 
     * @param clazz 类型
     * @return 对象字符串构造器
     */
    public static StringHepler.ObjectToStringBuilder toString(Class<?> clazz) {
        return StringHepler.build(clazz);
    }

    /**
     * 将map转换为字符串
     * 
     * @param map               map对象
     * @param separator         entry分割符号
     * @param keyValueSeparator kv分割符号
     * @return string
     */
    public static String toString(Map<?, ?> map, String separator, String keyValueSeparator) {
        return StringHepler.mapToString(map, separator, keyValueSeparator);
    }

    /**
     * 将传入的字符串转为可读的字符串对象
     * 
     * @param list      集合
     * @param open      字符串头
     * @param close     字符串尾
     * @param separator 分隔符
     * @return 转换后的字符串
     */
    public static String toString(Collection<?> list, String open, String close, String separator) {
        return StringHepler.listToString(list, open, close, separator);
    }

    /**
     * 获取stream字符串并关闭stream
     * 
     * @param in inputstream
     * @return UTF8字符串,读取不到会返回null
     */
    public static String toString(InputStream in) {
        return IOHepler.getStrAndClose(in);
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
     * 判断字符串是否为null
     * 字符串为"null",也会返回true
     * 
     * @param str 判断对象
     * @return true or false
     */
    public static boolean isNullString(String str) {
        return ObjectHepler.isNullString(str);
    }

    /**
     * 判断字符是否是空白符
     * 
     * @param ch 字符
     * @return 是空白符，则返回true
     */
    public static boolean isWhiteSpace(Character ch) {
        return ObjectHepler.isWhiteSpace(ch);
    }

    /**
     * 判断字符是否是数字
     * 
     * @param ch 字符
     * @return 是数字，则返回true
     */
    public static boolean isDigit(Character ch) {
        return ObjectHepler.isDigit(ch);
    }

    /**
     * 判断字符是否在十六进制范围
     * 
     * @param ch 字符
     * @return 是十六进制范围字符，则返回true
     */
    public static boolean isHex(Character ch) {
        return ObjectHepler.isHex(ch);
    }

    /**
     * 对象转换工具类
     * 
     * @return 对象转换工具类单例对象
     */
    public static ObjectCastHepler castHepler() {
        return ObjectCastHepler.INSTANCE;
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
     * 当前线程睡眠，单位为秒
     * 
     * @param seconds 睡眠时长/秒
     */
    public static void sleep(int seconds) {
        ThreadHepler.sleep(seconds);
    }

    /**
     * 当前线程睡眠
     * 
     * @param times    时长
     * @param timeUnit 时间单位
     */
    public static void sleep(int times, TimeUnit timeUnit) {
        ThreadHepler.sleep(times, timeUnit);
    }

    /**
     * 线程批量启动
     * 
     * @param threads 线程数组
     */
    public static void batchStart(Thread... threads) {
        ThreadHepler.batchStart(threads);
    }

    /**
     * 线程批量启动
     * 
     * @param list 线程集合
     */
    public static void batchStart(Collection<Thread> list) {
        ThreadHepler.batchStart(list);
    }

    /**
     * 线程批量启动
     * 
     * @param stream 线程流
     */
    public static void batchStart(Stream<Thread> stream) {
        ThreadHepler.batchStart(stream);
    }

    /**
     * 线程批量打断
     * 
     * @param threads 线程数组
     */
    public static void batchInterrupt(Thread... threads) {
        ThreadHepler.batchInterrupt(threads);
    }

    /**
     * 线程批量打断
     * 
     * @param list 线程集合
     */
    public static void batchInterrupt(Collection<Thread> list) {
        ThreadHepler.batchInterrupt(list);
    }

    /**
     * 线程批量打断
     * 
     * @param stream 线程流
     */
    public static void batchInterrupt(Stream<Thread> stream) {
        ThreadHepler.batchInterrupt(stream);
    }

    /**
     * 新建一个命名线程工厂
     * 
     * @param threadPoolName 线程池名称
     * @param daemon         是否为守护线程
     * @return 命名线程工厂
     */
    public static ThreadFactory newNamedThreadFactory(String threadPoolName, boolean daemon) {
        return ThreadHepler.newNamedThreadFactory(threadPoolName, daemon);
    }

    /**
     * 支持自定义任务队列数量，防止出现OOM
     * 
     * @param threads        固定线程数量
     * @param queueSize      任务队列数量
     * @param threadPoolName 线程池名称
     * @return 新建线程池
     */
    public static ExecutorService newFixedThreadPool(int threads, int queueSize, String threadPoolName) {
        return ThreadHepler.newFixedThreadPool(threads, queueSize, threadPoolName);
    }

    /**
     * 支持自定义最大线程数量，防止出现CPU100%情况
     * 
     * @param maxThreads     最大线程数量
     * @param threadPoolName 线程池名称
     * @return 新建线程池
     */
    public static ExecutorService newCachedThreadPool(int maxThreads, String threadPoolName) {
        return ThreadHepler.newCachedThreadPool(maxThreads, threadPoolName);
    }

    /**
     * 执行flush和close操作，并忽略掉可能发生的异常
     * 
     * @param outputStream 实现了OutputStream接口的对象
     */
    public static void flushAndClose(OutputStream outputStream) {
        IOHepler.flushAndClose(outputStream);
    }

    /**
     * 执行flush和close操作，并忽略掉可能发生的异常
     * 
     * @param writer 实现了Writer接口的对象
     */
    public static void flushAndClose(Writer writer) {
        IOHepler.flushAndClose(writer);
    }

    /**
     * 执行close操作，并忽略掉可能发生的异常
     * 
     * @param closeable closeable对象,例如InputStream对象
     */
    public static void close(Closeable closeable) {
        IOHepler.close(closeable);
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

    /**
     * 获取resources下文件流并转换为字符串返回
     * 
     * @param filePath 相对文件路径
     * @return UTF8字符串,读取不到会返回null
     */
    public static String getResourceAsStr(String filePath) {
        return IOHepler.getResourceAsStr(filePath);
    }

    /**
     * 读取resources下的properties文件
     * 
     * @param filePath 相对文件路径
     * @return PropertiesResource对象,UTF8编码
     * @throws IOException 读取失败报IOException异常
     */
    public static PropertiesResource loadFromResource(String filePath) throws IOException {
        return PropertiesResource.loadFromResource(filePath);
    }

    /**
     * 读取path对应的properties文件
     * 
     * @param path 文件对象
     * @return PropertiesResource对象,UTF8编码
     * @throws IOException 读取失败报IOException异常
     */
    public static PropertiesResource loadFromPath(Path path) throws IOException {
        return PropertiesResource.loadFromPath(path);
    }

}
