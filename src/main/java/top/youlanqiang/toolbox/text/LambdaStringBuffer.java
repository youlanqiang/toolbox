package top.youlanqiang.toolbox.text;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * 基于线程安全的StringBuffer构造的LambdaStringBuilder类
 * 
 * @author youlanqiang
 *         created in 2022/12/15 23:2
 * 
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

    @Override
    public AbstractLambdaStringBuilder append(Object obj) {
        if (obj instanceof Collection<?> list) {
            return append(list);
        } else if (obj instanceof Map<?, ?> map) {
            return append(map);
        } else {
            buffer.append(obj.toString());
        }
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder append(Object... objs) {
        return append(Arrays.asList(objs));
    }

    @Override
    public AbstractLambdaStringBuilder append(boolean condition, Object obj) {
        if (condition) {
            return append(obj);
        }
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder append(boolean condition, Object... objs) {
        if (condition) {
            return append(objs);
        }
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder append(Collection<?> list) {
        return append(list, PACKAGE_CONST.DEFAULT_ARRAY_SEPARATOR);
    }

    @Override
    public AbstractLambdaStringBuilder append(Collection<?> list, String separator) {
        list.forEach(item -> buffer.append(item).append(separator));
        return this.deleteLast(separator.length());
    }

    @Override
    public AbstractLambdaStringBuilder append(Map<?, ?> map) {
        return append(map, PACKAGE_CONST.DEFAULT_K_V_SEPARATOR, PACKAGE_CONST.DEFAULT_SEPARATOR);
    }

    @Override
    public AbstractLambdaStringBuilder append(Map<?, ?> map, String keyValueSeparator, String separator) {
        map.forEach((k, v) -> {
            buffer
                    .append(Objects.toString(k))
                    .append(keyValueSeparator)
                    .append(Objects.toString(v))
                    .append(separator);
        });
        return this.deleteLast(separator.length());
    }

    @Override
    public AbstractLambdaStringBuilder setCharAt(int index, char ch) {
        buffer.setCharAt(index, ch);
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder repeat(int count) {
        if (count <= 0) {
            return this;
        }

        buffer.append(buffer.toString().repeat(count - 1));

        return this;
    }

    @Override
    public AbstractLambdaStringBuilder delete(int start, int end) {
        buffer.delete(start, end);
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder deleteFirstChar() {
        return deleteFirst(1);
    }

    @Override
    public AbstractLambdaStringBuilder deleteFirst(int size) {
        buffer.delete(0, size);
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder deleteLastChar() {
        return deleteLast(1);
    }

    @Override
    public AbstractLambdaStringBuilder deleteLast(int size) {
        int lastIndex = buffer.length() - size;
        buffer.delete(lastIndex, buffer.length());
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder clean() {
        buffer.delete(0, buffer.length());
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder reverse() {
        buffer.reverse();
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder toLowerCase() {
        String tempStr = buffer.toString().toLowerCase();
        buffer.insert(0, tempStr);
        return this;
    }

    @Override
    public AbstractLambdaStringBuilder toUpperCase() {
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
    public int compareTo(StringBuffer arg) {
        return buffer.compareTo(arg);
    }

    @Override
    public boolean equals(Object obj) {
        return buffer.toString().equals(obj);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return buffer.subSequence(start, end);
    }

}
