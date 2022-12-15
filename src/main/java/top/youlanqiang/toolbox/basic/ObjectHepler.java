package top.youlanqiang.toolbox.basic;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * @author youlanqiang
 *         created in 2022/12/06 21:44
 *         对象工具类
 */
public final class ObjectHepler {

    private ObjectHepler() {
    }

    /**
     * 判断value是否为空
     * 
     * @param value
     * @return true or false
     */
    public static boolean isEmpty(Object value) {
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
     * 复制source中的属性对象至target对象
     * 
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        // todo
    }

    /**
     * 深拷贝一个对象
     * 
     * @param <T>
     * @param source 源对象
     * @return 深拷贝后新对象
     */
    public static <T> T copy(T source) {
        return null;
    }

}
