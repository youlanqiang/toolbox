package top.youlanqiang.toolbox.base;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * 对象工具类
 * 
 * @author youlanqiang
 *         created in 2022/12/06 21:44
 * 
 */
public final class ObjectHepler {

    private ObjectHepler() {
    }

    /**
     * 判断value对象是否为空
     * 
     * @param value 判断对象
     * @return 为空返回true
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }

        // 将估计最频繁使用的类型首先判断
        if (value instanceof CharSequence) {
            return ((CharSequence) value).length() == 0;
        } else if (value instanceof Collection) {
            return ((Collection<?>) value).isEmpty();
        } else if (value instanceof Map) {
            return ((Map<?, ?>) value).isEmpty();
        } else if (value instanceof java.util.Optional) {
            return !((java.util.Optional<?>) value).isPresent();
        } else if (value instanceof OptionalInt) {
            return !((OptionalInt) value).isPresent();
        } else if (value instanceof OptionalLong) {
            return !((OptionalLong) value).isPresent();
        } else if (value instanceof OptionalDouble) {
            return !((OptionalDouble) value).isPresent();
        } else if (value instanceof Optional) {
            return !((Optional<?>) value).isPresent();
        } else if (value.getClass().isArray()) {
            return Array.getLength(value) == 0;
        }
        return false;
    }

    /**
     * todo 未完成
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {

            e.printStackTrace();
        }
        return null;
    }

}
