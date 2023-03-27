package top.youlanqiang.toolbox.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import top.youlanqiang.toolbox.base.ObjectHepler;

/**
 * 支持自定义命名的ThreadFactory
 * 
 * @author youlanqiang
 */
public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger POOL_SEQ = new AtomicInteger(1);

    private final ThreadGroup group;

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;

    private final boolean daemon;

    /**
     * 构造方法
     * 
     * @param name   线程名称前缀
     * @param daemon 是否为守护线程
     */
    public NamedThreadFactory(String name, boolean daemon) {
        if (ObjectHepler.isEmpty(name)) {
            name = "pool-";
        }
        this.group = Thread.currentThread().getThreadGroup();
        this.namePrefix = name + "-" + POOL_SEQ.getAndIncrement() + "-thread-";
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        t.setDaemon(daemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

    /**
     * 获取线程组
     * 
     * @return 当前ThreadFactory的线程组
     */
    public ThreadGroup getThreadGroup() {
        return this.group;
    }

}
