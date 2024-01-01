package top.youlanqiang.toolbox.base;

import java.util.Collection;
import java.util.Map;

/**
 * 常用的集合工具类
 */
public final class CollectionUtils {

    /**
     * 判断集合是否为空或null
     * @param collection 进行判断的目标
     * @return 集合为空或为null则返回true
     */
    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断map是否为空或null
     * @param map 进行判断的目标
     * @return map为空或为null则返回true
     */
    public static boolean isEmpty(Map<?, ?> map){
        return map == null || map.isEmpty();
    }

    /**
     * 判断数组是否为空或null
     * @param objects  数组
     * @return objects为空或为null则返回true
     */
    @SafeVarargs
    public static <T> boolean isEmpty(T... objects ){
        return objects == null || objects.length == 0;
    }


    private CollectionUtils(){}
}
