package top.youlanqiang.toolbox.base;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO工具类
 * 
 * @author youlanqiang
 *         created in 2023/01/11 20:20
 * 
 */
public final class IOHepler {

    /**
     * 执行close操作，并忽略掉可能发生的异常
     * 
     * @param closeable closeable对象,例如InputStream对象
     */
    public static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            // ignore exception
        }
    }

}
