package top.youlanqiang.toolbox.base;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @author youlanqiang
 *         created in 2022/12/15 23:2
 *         基于线程安全的StringBuffer构造的LambdaStringBuilder类
 */
public class LambdaStringBuffer implements AbstractLambdaStringBuilder {

    private final StringBuffer buffer;

    /**
     * 新建一个空的LambdaStringBuffer类
     */
    public LambdaStringBuffer() {
        this.buffer = new StringBuffer();
    }

    /**
     * 新建一个默认字符串为str的LambdaStringBuffer类
     * 
     * @param str 默认字符串
     */
    public LambdaStringBuffer(String str) {
        this.buffer = new StringBuffer(str);
    }

    public LambdaStringBuffer append(Object obj) {
        buffer.append(obj);
        return this;
    }

    public LambdaStringBuffer append(Object... objs) {
        buffer.append(objs);
        return this;
    }

    public LambdaStringBuffer append(boolean condition, Object obj) {
        if (condition) {
            buffer.append(obj);
        }
        return this;
    }

    public LambdaStringBuffer append(boolean condition, Object... objs) {
        if (condition) {
            buffer.append(objs);
        }
        return this;
    }

    public LambdaStringBuffer append(Collection<?> list) {
        return append(list, "");
    }

    public LambdaStringBuffer append(Collection<?> list, String separator) {
        list.forEach(item -> buffer.append(item).append(separator));
        return this.deleteLast(separator.length());
    }

    public LambdaStringBuffer append(Map<?, ?> map, String keyValueSeparator, String separator) {
        map.forEach((k, v) -> {
            buffer
                    .append(Objects.toString(k))
                    .append(keyValueSeparator)
                    .append(Objects.toString(v))
                    .append(separator);
        });
        return this.deleteLast(separator.length());
    }

    public LambdaStringBuffer setCharAt(int index, char ch) {
        buffer.setCharAt(index, ch);
        return this;
    }

    public LambdaStringBuffer repeat(int count) {
        String tempStr = buffer.toString();
        IntStream.of(count).forEach(x -> {
            buffer.append(tempStr);
        });
        return this;
    }

    public LambdaStringBuffer delete(int start, int end) {
        buffer.delete(start, end);
        return this;
    }

    public LambdaStringBuffer deleteFirstChar() {
        return deleteFirst(1);
    }

    public LambdaStringBuffer deleteFirst(int size) {
        buffer.delete(0, size);
        return this;
    }

    public LambdaStringBuffer deleteLastChar() {
        return deleteLast(1);
    }

    public LambdaStringBuffer deleteLast(int size) {
        int lastIndex = buffer.length() - 1;
        buffer.delete(lastIndex - size, lastIndex);
        return this;
    }

    public LambdaStringBuffer clean() {
        buffer.delete(0, buffer.length());
        return this;
    }

    public LambdaStringBuffer reverse() {
        buffer.reverse();
        return this;
    }

    public LambdaStringBuffer toLowerCase() {
        String tempStr = buffer.toString().toLowerCase();
        buffer.insert(0, tempStr);
        return this;
    }

    public LambdaStringBuffer toUpperCase() {
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
     * 使用StringBuffer默认的compareTo比较大小
     * 
     * @param arg 比较值
     * @return 1,0,-1
     */
    public int compareTo(StringBuffer arg) {
        return buffer.compareTo(arg);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
