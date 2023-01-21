package top.youlanqiang.toolbox.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合Reader类
 * 
 * @author youlanqiang
 */
public class ListReader<T> {

    private List<T> list = null;

    private int pos = 0;

    public static ListReader<?> asArrayList() {
        return new ListReader<>(new ArrayList<>());
    }

    private ListReader(List<T> list) {
        this.list = list;
    }

    public T peek() {
        return pos < list.size() ? list.get(pos) : null;
    }

    public T peekPrevious() {
        return pos - 1 < 0 ? null : list.get(pos - 1);
    }

    public T next() {
        return list.get(pos++);
    }

    public boolean hasMore() {
        return pos < list.size();
    }

    @Override
    public String toString() {
        return ToStringHepler.listToString(list, "[", "]", ",");
    }
}
