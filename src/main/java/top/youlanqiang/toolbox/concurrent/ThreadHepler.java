package top.youlanqiang.toolbox.concurrent;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author youlanqiang
 *         Thread线程工具类
 */
public final class ThreadHepler {

    private ThreadHepler() {
    }

    /**
     * 当前线程睡眠，单位为秒
     * 
     * @param seconds 睡眠时长/秒
     */
    public static void sleep(int seconds) {
        sleep(seconds, TimeUnit.SECONDS);
    }

    /**
     * 当前线程睡眠
     * 
     * @param times    时长
     * @param timeUnit 时间单位
     */
    public static void sleep(int times, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(times);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量启动
     * 
     * @param threads 线程数组
     */
    public static void batchStart(Thread... threads) {
        batchStart(Stream.of(threads));
    }

    /**
     * 批量启动
     * 
     * @param list 线程集合
     */
    public static void batchStart(Collection<Thread> list) {
        batchStart(list.stream());
    }

    /**
     * 批量启动
     * 
     * @param stream 线程流
     */
    public static void batchStart(Stream<Thread> stream) {
        stream.forEach(Thread::start);
    }

    /**
     * 批量打断
     * 
     * @param threads 线程数组
     */
    public static void batchInterrupt(Thread... threads) {
        batchInterrupt(Stream.of(threads));
    }

    /**
     * 批量打断
     * 
     * @param list 线程集合
     */
    public static void batchInterrupt(Collection<Thread> list) {
        batchInterrupt(list.stream());
    }

    /**
     * 批量打断
     * 
     * @param stream 线程流
     */
    public static void batchInterrupt(Stream<Thread> stream) {
        stream.forEach(Thread::interrupt);
    }

}
