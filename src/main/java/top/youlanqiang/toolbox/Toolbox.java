package top.youlanqiang.toolbox;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

import top.youlanqiang.toolbox.basic.EqualsHepler;
import top.youlanqiang.toolbox.basic.ObjectHepler;
import top.youlanqiang.toolbox.basic.ToStringHepler;
import top.youlanqiang.toolbox.collection.Pair;
import top.youlanqiang.toolbox.collection.Triple;
import top.youlanqiang.toolbox.concurrent.ThreadHepler;

/**
 * @author youlanqiang
 *         created in 2022/12/03 09:30
 *         常用工具类
 */
public final class Toolbox {

    private Toolbox() {
    }

    /**
     * @see Triple
     *
     * @param left   左值
     * @param middle 中值
     * @param right  右值
     * @return 不可变Triple对象
     */
    public static <L, M, R> Triple<L, M, R> ofTriple(L left, M middle, R right) {
        return Triple.of(left, middle, right);
    }

    /**
     * @see Triple
     *
     * @return 可变Triple对象
     */
    public static <L, M, R> Triple<L, M, R> buildTriple() {
        return Triple.build();
    }

    /**
     * @see Pair
     * 
     * @param left  左值
     * @param right 右值
     * @return 不可变Pair对象
     */
    public static <L, R> Pair<L, R> ofPair(L left, R right) {
        return Pair.of(left, right);
    }

    /**
     * @see Pair
     * 
     * @return 可变Pair对象
     */
    public static <L, R> Pair<L, R> buildPair() {
        return Pair.build();
    }

    public static <L, R> Pair<L, R> buildPair(L left, R right) {
        return Pair.build(left, right);
    }

    /**
     * 创建一个比较器
     * 
     * @return
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
     * @param pattern 字符串模版
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
     * 创建内部类ObjectToStringBuilder，传入class的类名
     * {@link ToStringHepler#build}
     * 
     * @param obj
     * @return
     */
    public static ToStringHepler.ObjectToStringBuilder toString(Object obj) {
        return ToStringHepler.build(obj);
    }

    /**
     * 创建内部类ObjectToStringBuilder，传入类型名称
     * {@link ToStringHepler#build}
     * 
     * @param className 类型的名称
     * @return
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
     * {@link ToStringHepler#build}
     * 
     * @param clazz 类型
     * @return
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
        return Objects.isNull(obj) || ObjectHepler.isEmpty(obj);
    }

    /**
     * 数组是否为空
     * 
     * @param objects 数组
     * @return true or false
     */
    public static boolean isEmpty(Object... objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * Map是否为空
     * 
     * @param map map对象
     * @return true or false
     */
    public static boolean isEmpty(Map<Object, Object> map) {
        return map == null || map.size() == 0;
    }

    /**
     * 集合是否为空
     * 
     * @param collection 集合对象
     * @return true or false
     */
    public static boolean isEmpty(Collection<Object> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * result为false，则抛出自定义的exception
     * 
     * @param result            断言结果
     * @param exceptionSupplier 自定义异常
     * @throws X
     */
    public static <X extends Throwable> void assertBoolean(boolean result,
            Supplier<? extends X> exceptionSupplier) throws X {
        if (!result) {
            throw exceptionSupplier.get();
        }
    }

    public static void sleep(int time) {
        ThreadHepler.sleep(time);
    }

    public static void sleep(int time, TimeUnit timeUnit) {
        ThreadHepler.sleep(time, timeUnit);
    }

    public static void batchStart(Thread... threads) {
        ThreadHepler.batchStart(threads);
    }

    public static void batchStart(Collection<Thread> list) {
        ThreadHepler.batchStart(list);
    }

    public static void batchStart(Stream<Thread> stream) {
        ThreadHepler.batchStart(stream);
    }

    public static void batchInterrupt(Thread... threads) {
        ThreadHepler.batchInterrupt(threads);
    }

    public static void batchInterrupt(Collection<Thread> list) {
        ThreadHepler.batchInterrupt(list);
    }

    public static void batchInterrupt(Stream<Thread> stream) {
        ThreadHepler.batchInterrupt(stream);
    }

}
