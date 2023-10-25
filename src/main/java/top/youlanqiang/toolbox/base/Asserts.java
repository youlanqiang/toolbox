package top.youlanqiang.toolbox.base;

/**
 * 断言工具类
 */
public final class Asserts {

    /**
     * object对象为null则直接抛出NullPointerException
     * 
     * @param object 校验对象
     */
    public static void notNull(Object object) {
        notNull(object, null);
    }

    /**
     * object对象为null则直接抛出NullPointerException
     * 
     * @param object  校验对象
     * @param message 异常信息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            if (message == null) {
                throw new NullPointerException();
            } else {
                throw new NullPointerException(message);
            }
        }
    }

}
