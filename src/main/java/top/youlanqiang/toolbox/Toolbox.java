package top.youlanqiang.toolbox;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import top.youlanqiang.toolbox.base.EqualsHepler;
import top.youlanqiang.toolbox.base.ObjectHepler;
import top.youlanqiang.toolbox.base.ToStringHepler;
import top.youlanqiang.toolbox.collection.Pair;
import top.youlanqiang.toolbox.collection.Triple;
import top.youlanqiang.toolbox.concurrent.ThreadHepler;

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
    public static <L, M, R> Triple<L, M, R> ofTriple(L left, M middle, R right) {
        return Triple.of(left, middle, right);
    }

    /**
     * 创建一个可变Triple对象
     * 
     * @param <L> 左值泛型
     * @param <M> 中值泛型
     * @param <R> 右值泛型
     * @return 可变Triple对象
     */
    public static <L, M, R> Triple<L, M, R> buildTriple() {
        return Triple.build();
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
    public static <L, R> Pair<L, R> ofPair(L left, R right) {
        return Pair.of(left, right);
    }

    /**
     * 创建一个可变的Pair空对象
     * 
     * @param <L> 左值泛型
     * @param <R> 右值泛型
     * @return MutablePair
     */
    public static <L, R> Pair<L, R> buildPair() {
        return Pair.build();
    }

    /**
     * 创建一个可变的Pair对象
     * 
     * @param <L>   左值泛型
     * @param <R>   右值泛型
     * @param left  左值
     * @param right 右值
     * @return MutablePair
     */
    public static <L, R> Pair<L, R> buildPair(L left, R right) {
        return Pair.build(left, right);
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
     * 使用默认的占位符 {} 格式化字符串内容
     * 
     * @param pattern 字符串模版
     * @param args    参数列表
     * @return 格式化后的格式化字符串
     */
    public static String format(String pattern, Object... args) {
        return ToStringHepler.format(pattern, args);
    }

    /**
     * 创建内部类ObjectToStringBuilder，传入obj对象会将对象class的simpleName设置为默认名称
     * 
     * @param obj 构造器的默认对象
     * @return 对象字符串构造器
     */
    public static ToStringHepler.ObjectToStringBuilder toString(Object obj) {
        return ToStringHepler.build(obj);
    }

    /**
     * 创建内部类ObjectToStringBuilder，传入class名称
     * 
     * @param className 类型的名称
     * @return 对象字符串构造器
     */
    public static ToStringHepler.ObjectToStringBuilder toString(String className) {
        return ToStringHepler.build(className);
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
        return ToStringHepler.mapToString(map, separator, keyValueSeparator);
    }

    /**
     * 创建内部类ObjectToStringBuilder，传入class的类名
     * 
     * @param clazz 类型
     * @return 对象字符串构造器
     */
    public static ToStringHepler.ObjectToStringBuilder toString(Class<?> clazz) {
        return ToStringHepler.build(clazz);
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

}
