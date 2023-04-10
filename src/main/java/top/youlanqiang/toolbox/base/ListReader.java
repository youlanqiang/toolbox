package top.youlanqiang.toolbox.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合Reader类,该类不是线程安全的,在多线程环境中不要使用.
 * 
 * @author youlanqiang
 */
public class ListReader<T> {

    private List<T> list = null;

    private int pos = 0;

    /**
     * 创建内部容器为ArrayList的ListReader类
     * 
     * @param <T> 泛型
     * @return ListReader
     */
    public static <T> ListReader<T> asArrayList() {
        return new ListReader<T>(new ArrayList<T>());
    }

    /**
     * 自定义一个ListReader类
     * 
     * @param <T>  泛型
     * @param list 内部容器
     * @return ListReader
     */
    public static <T> ListReader<T> of(List<T> list) {
        return new ListReader<>(list);
    }

    private ListReader(List<T> list) {
        this.list = list;
    }

    /**
     * 向List容器中添加item
     * 
     * @param item item
     */
    public void add(T item) {
        this.list.add(item);
    }

    /**
     * 获取当前下标下的item，可能为null
     * 
     * @return item
     */
    public T peek() {
        return pos < list.size() ? list.get(pos) : null;
    }

    /**
     * 返回上一个下标下的item，可能为null
     * 
     * @return item
     */
    public T peekPrevious() {
        return pos - 1 < 0 ? null : list.get(pos - 1);
    }

    /**
     * 获取当前下标下的item，并将pos加1
     * 
     * @return 当前下标的item
     */
    public T next() {
        return list.get(pos++);
    }

    /**
     * pos减1，不会出现负数情况，最小为0
     */
    public void back() {
        // 确保不会出现负数下标
        this.pos = Math.max(0, --pos);
    }

    /**
     * 判断list容器是否还有item
     * 
     * @return true or false
     */
    public boolean hasMore() {
        return pos < list.size();
    }

    /**
     * list容器的size
     * 
     * @return size
     */
    public int size() {
        return list.size();
    }

    /**
     * 判断容器是否为null，或为空
     * 
     * @return true or false
     */
    public boolean isEmpty() {
        return list == null || list.isEmpty();
    }

    @Override
    public String toString() {
        return StringHepler.listToString(list, "[", "]", ",");
    }
}
