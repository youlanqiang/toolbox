package top.youlanqiang.toolbox.base;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * 基于线程不安全的StringBuilder构造的LambdaStringBuilder类
 * 单线程下性能更好
 * 
 * @author youlanqiang
 *         created in 2022/12/15 23:2
 * 
 */
public class LambdaStringBuilder implements AbstractLambdaStringBuilder {

    private final StringBuilder buffer;

    /**
     * 新建一个空的LambdaStringBuilder类
     */
    public LambdaStringBuilder() {
        this.buffer = new StringBuilder();
    }

    /**
     * 新建一个默认字符串为str的LambdaStringBuilder类
     * 
     * @param str 默认字符串
     */
    public LambdaStringBuilder(String str) {
        this.buffer = new StringBuilder(str);
    }

    public LambdaStringBuilder append(Object obj) {
        buffer.append(obj);
        return this;
    }

    public LambdaStringBuilder append(Object... objs) {
        buffer.append(objs);
        return this;
    }

    public LambdaStringBuilder append(boolean condition, Object obj) {
        if (condition) {
            buffer.append(obj);
        }
        return this;
    }

    public LambdaStringBuilder append(boolean condition, Object... objs) {
        if (condition) {
            buffer.append(objs);
        }
        return this;
    }

    public LambdaStringBuilder append(Collection<?> list) {
        return append(list, "");
    }

    public LambdaStringBuilder append(Collection<?> list, String separator) {
        list.forEach(item -> buffer.append(item).append(separator));
        return this.deleteLast(separator.length());
    }

    public LambdaStringBuilder append(Map<?, ?> map, String keyValueSeparator, String separator) {
        map.forEach((k, v) -> {
            buffer
                    .append(Objects.toString(k))
                    .append(keyValueSeparator)
                    .append(Objects.toString(v))
                    .append(separator);
        });
        return this.deleteLast(separator.length());
    }

    public LambdaStringBuilder setCharAt(int index, char ch) {
        buffer.setCharAt(index, ch);
        return this;
    }

    public LambdaStringBuilder repeat(int count) {
        String tempStr = buffer.toString();
        IntStream.of(count).forEach(x -> {
            buffer.append(tempStr);
        });
        return this;
    }

    public LambdaStringBuilder delete(int start, int end) {
        buffer.delete(start, end);
        return this;
    }

    public LambdaStringBuilder deleteFirstChar() {
        return deleteFirst(1);
    }

    public LambdaStringBuilder deleteFirst(int size) {
        buffer.delete(0, size);
        return this;
    }

    public LambdaStringBuilder deleteLastChar() {
        return deleteLast(1);
    }

    public LambdaStringBuilder deleteLast(int size) {
        int lastIndex = buffer.length() - 1;
        buffer.delete(lastIndex - size, lastIndex);
        return this;
    }

    public LambdaStringBuilder clean() {
        buffer.delete(0, buffer.length());
        return this;
    }

    public LambdaStringBuilder reverse() {
        buffer.reverse();
        return this;
    }

    public LambdaStringBuilder toLowerCase() {
        String tempStr = buffer.toString().toLowerCase();
        buffer.insert(0, tempStr);
        return this;
    }

    public LambdaStringBuilder toUpperCase() {
        String tempStr = buffer.toString().toUpperCase();
        buffer.insert(0, tempStr);
        return this;
    }

    public int length() {
        return buffer.length();
    }

    public int capacity() {
        return buffer.capacity();
    }

    public char charAt(int index) {
        return buffer.charAt(index);
    }

    /**
     * 使用StringBuilder默认的compareTo比较大小
     * 
     * @param arg 比较值
     * @return 1,0,-1
     */
    public int compareTo(StringBuilder arg) {
        return buffer.compareTo(arg);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
