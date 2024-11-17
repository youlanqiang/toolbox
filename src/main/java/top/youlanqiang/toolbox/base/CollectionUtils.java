package top.youlanqiang.toolbox.base;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 常用的集合工具类
 */
public final class CollectionUtils {

    /**
     * 判断集合是否为空或null
     * 
     * @param collection 进行判断的目标
     * @return 集合为空或为null则返回true
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断map是否为空或null
     * 
     * @param map 进行判断的目标
     * @return map为空或为null则返回true
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断数组是否为空或null
     * 
     * @param objects 数组
     * @return objects为空或为null则返回true
     */
    @SafeVarargs
    public static <T> boolean isEmpty(T... objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * 创建一个空的集合对象
     * 
     * @param <T>        集合元素的泛型
     * @param collection 集合对象
     * @return 空的集合对象
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> createCollectionInstance(Collection<T> collection) {
        try {
            return collection.getClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对集合中的每个元素都执行给定的操作
     * 
     * @param <T>        集合中对象的泛型
     * @param collection 集合
     * @param action     执行操作
     */
    public static <T> void forEach(Collection<T> collection, Consumer<T> action) {
        if (CollectionUtils.isEmpty(collection)) {
            return;
        }
        for (T item : collection) {
            action.accept(item);
        }
    }

    /**
     * 对Collection对象的数据元素进行过滤，返回一个新的collection对象
     * 
     * @param <T>        集合中对象的泛型
     * @param collection collection对象
     * @param predicate  过滤条件
     * @return 一个新的collection对象
     */
    public static <T> Collection<T> filter(Collection<T> collection, Predicate<T> predicate) {
        Collection<T> result = createCollectionInstance(collection);
        for (T item : collection) {
            if (predicate.test(item)) {
                result.add(item);
            }

        }
        return result;
    }

    /**
     * 对Collection对象的数据元素进行map转换，返回一个新的collection对象
     * 
     * @param <T>        Collection原始对象元素的泛型
     * @param <R>        转换后的泛型对象
     * @param collection 集合对象
     * @param mapper     转换方法
     * @return 一个新的collection对象
     */
    public static <T, R> Collection<R> map(Collection<T> collection, Function<T, R> mapper) {
        @SuppressWarnings("unchecked")
        Collection<R> result = (Collection<R>) createCollectionInstance(collection);
        for (T item : collection) {
            result.add(mapper.apply(item));
        }
        return result;
    }

    private CollectionUtils() {
    }
}
