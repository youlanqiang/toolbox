package top.youlanqiang.toolbox.concurrent;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public final class ThreadHepler {

    private ThreadHepler() {
    }

    public static void sleep(int time) {
        sleep(time, TimeUnit.SECONDS);
    }

    public static void sleep(int time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void batchStart(Thread... threads) {
        batchStart(Stream.of(threads));
    }

    public static void batchStart(Collection<Thread> list) {
        batchStart(list.stream());
    }

    public static void batchStart(Stream<Thread> stream) {
        stream.forEach(Thread::start);
    }

    public static void batchInterrupt(Thread... threads) {
        batchInterrupt(Stream.of(threads));
    }

    public static void batchInterrupt(Collection<Thread> list) {
        batchInterrupt(list.stream());
    }

    public static void batchInterrupt(Stream<Thread> stream) {
        stream.forEach(Thread::interrupt);
    }

}
