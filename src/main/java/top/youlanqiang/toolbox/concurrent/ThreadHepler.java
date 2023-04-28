package top.youlanqiang.toolbox.concurrent;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Thread线程工具类
 * 
 * @author youlanqiang
 * 
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
     * 批量执行Runnable
     * 
     * @param threadSize 执行次数
     * @param runnable   runnable对象
     */
    public static void run(int threadSize, Runnable runnable) {
        if (threadSize > 0) {
            var executor = newFixedThreadPool(threadSize, threadSize, "test");
            for (int i = 0; i < threadSize; i++) {
                executor.execute(runnable);
            }
            executor.shutdown();
        }
    }

    /**
     * 线程批量启动
     * 
     * @param threads 线程数组
     */
    public static void batchStart(Thread... threads) {
        batchStart(Stream.of(threads));
    }

    /**
     * 线程批量启动
     * 
     * @param list 线程集合
     */
    public static void batchStart(Collection<Thread> list) {
        batchStart(list.stream());
    }

    /**
     * 线程批量启动
     * 
     * @param stream 线程流
     */
    public static void batchStart(Stream<Thread> stream) {
        stream.forEach(Thread::start);
    }

    /**
     * 线程批量打断
     * 
     * @param threads 线程数组
     */
    public static void batchInterrupt(Thread... threads) {
        batchInterrupt(Stream.of(threads));
    }

    /**
     * 线程批量打断
     * 
     * @param list 线程集合
     */
    public static void batchInterrupt(Collection<Thread> list) {
        batchInterrupt(list.stream());
    }

    /**
     * 线程批量打断
     * 
     * @param stream 线程流
     */
    public static void batchInterrupt(Stream<Thread> stream) {
        stream.forEach(Thread::interrupt);
    }

    /**
     * 新建一个命名线程工厂
     * 
     * @param threadPoolName 线程池名称
     * @param daemon         是否为守护线程
     * @return 命名线程工厂
     */
    public static ThreadFactory newNamedThreadFactory(String threadPoolName, boolean daemon) {
        return new NamedThreadFactory(threadPoolName, false);
    }

    /**
     * {@link Executors#newFixedThreadPool }
     * 支持自定义任务队列数量，防止出现OOM
     * 创建一个固定大小的线程池，可控制线程最大并发数，超出的线程会在队列中等待。
     * 
     * @param threads        固定线程数量
     * @param queueSize      任务队列数量
     * @param threadPoolName 线程池名称
     * @return 新建线程池
     */
    public static ExecutorService newFixedThreadPool(int threads, int queueSize, String threadPoolName) {
        return new ThreadPoolExecutor(threads, threads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(queueSize),
                new NamedThreadFactory(threadPoolName, false));
    }

    /**
     * {@link Executors#newCachedThreadPool }
     * 支持自定义最大线程数量，防止出现CPU100%情况
     * 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，否则新建线程。
     * 
     * @param maxThreads     最大线程数量
     * @param threadPoolName 线程池名称
     * @return 新建线程池
     */
    public static ExecutorService newCachedThreadPool(int maxThreads, String threadPoolName) {
        return new ThreadPoolExecutor(0, maxThreads,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new NamedThreadFactory(threadPoolName, false));
    }

}
