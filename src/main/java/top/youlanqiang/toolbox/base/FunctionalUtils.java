package top.youlanqiang.toolbox.base;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 函数相关工具类
 */
public final class FunctionalUtils {

    private FunctionalUtils() {
    }

    /**
     * 从Supplier获取值，并返回
     * 
     * @param <T>      Supplier对象泛型
     * @param supplier Supplier对象
     * @return 对象的返回值
     */
    public static <T> T get(Supplier<T> supplier) {
        return supplier.get();
    }

    /**
     * 消费者对象消费item对象
     * 
     * @param <T>      Consumer对象泛型
     * @param item     待消费的对象
     * @param consumer Consumer对象
     */
    public static <T> void consume(T item, Consumer<T> consumer) {
        consumer.accept(item);
    }

    /**
     * 执行函数对象消费item输出R类型
     * 
     * @param <T>      待消费的对象泛型
     * @param <R>      输出的对象泛型
     * @param item     待消费的对象
     * @param function 函数对象
     * @return 经过function对象转换后的输出
     */
    public static <T, R> R apply(T item, Function<T, R> function) {
        return function.apply(item);
    }

    /**
     * 重复执行operation提供的操作，直到满足条件condition
     * 
     * @param <T>       对象泛型
     * @param condition 条件对象
     * @param operation 执行操作
     */
    public static <T> void repeatUntil(Supplier<Boolean> condition, Supplier<T> operation) {
        while (!condition.get()) {
            operation.get();
        }
    }

    /**
     * 执行一个runnable对象，并捕获其中的异常
     * 
     * @param operation        runnable对象
     * @param exceptionHandler 异常捕获器
     */
    public static void runSafely(Runnable operation, Consumer<Exception> exceptionHandler) {
        try {
            operation.run();
        } catch (Exception e) {
            exceptionHandler.accept(e);
        }
    }

    /**
     * 执行一个条件检查，如果条件为真，则执行操作
     * 
     * @param condition 条件检查
     * @param action    执行检查操作
     */
    public static void doIf(Supplier<Boolean> condition, Runnable action) {
        if (condition.get()) {
            action.run();
        }
    }

    /**
     * 执行action操作，并返回状态，用于链式操作中的状态检查
     * 
     * @param action 操作
     * @return true or false
     */
    public static boolean tryPerform(Runnable action) {
        try {
            action.run();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 执行一连串的操作，如果失败则不会执行未执行的操作，直接执行rollback操作
     * 
     * @param rollbackHandler 回滚操作
     * @param operations      一连串的操作
     * @return true of false
     */
    public static boolean performTransaction(Runnable rollbackHandler,
            @SuppressWarnings("unchecked") Supplier<Boolean>... operations) {
        boolean success = true;
        for (Supplier<Boolean> operation : operations) {
            if (!operation.get()) {
                success = false;
                break;
            }
        }

        // 执行回滚操作
        if (!success) {
            rollbackHandler.run();
        }

        return success;
    }

}
