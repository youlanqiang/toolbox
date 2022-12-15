package top.youlanqiang.toolbox.basic;

import java.util.Collection;
import java.util.stream.IntStream;

/**
 * @author youlanqiang
 *         created in 2022/12/15 23:2
 *         LambdaStringBuffer
 *         流式StringBuffer，在JDK提供的StringBuffer基础上提供了更多的操作
 */
public class LambdaStringBuffer {

    private final StringBuffer buffer;

    public LambdaStringBuffer() {
        this.buffer = new StringBuffer();
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

    /**
     * 循环添加Collection中的对象，默认分隔符为空字符串
     * 
     * @param list list对象
     * @return this
     */
    public LambdaStringBuffer append(Collection<?> list) {
        return append(list, "");
    }

    /**
     * 循环添加Collection中的对象
     * 
     * @param list      list对象
     * @param separator 指定分隔符
     * @return this
     */
    public LambdaStringBuffer append(Collection<?> list, String separator) {
        list.forEach(item -> buffer.append(item).append(separator));
        return this.deleteLast(separator.length());
    }

    public LambdaStringBuffer setCharAt(int index, char ch) {
        buffer.setCharAt(index, ch);
        return this;
    }

    /**
     * 给定字符串的重复 count 次的串联
     * 
     * @param count 重复次数
     * @return this
     */
    public LambdaStringBuffer repeat(int count) {
        String tempStr = buffer.toString();
        IntStream.of(count).forEach(x -> {
            buffer.append(tempStr);
        });
        return this;
    }

    /**
     * 删除开始下标到结束下标的字段
     * 
     * @param start 开始下标
     * @param end   结束下标
     * @return this
     */
    public LambdaStringBuffer delete(int start, int end) {
        buffer.delete(start, end);
        return this;
    }

    /**
     * 删除开头第一个字符
     * 
     * @return
     */
    public LambdaStringBuffer deleteFirstChar() {
        return deleteFirst(1);
    }

    /**
     * 删除开头size长度的字符串
     * 
     * @param size 长度
     * @return this
     */
    public LambdaStringBuffer deleteFirst(int size) {
        buffer.delete(0, size);
        return this;
    }

    /**
     * 删除结尾最后一个字符
     * 
     * @return this
     */
    public LambdaStringBuffer deleteLastChar() {
        return deleteLast(1);
    }

    /**
     * 删除结尾size长度的字符串
     * 
     * @param size 长度
     * @return this
     */
    public LambdaStringBuffer deleteLast(int size) {
        int lastIndex = buffer.length() - 1;
        buffer.delete(lastIndex - size, lastIndex);
        return this;
    }

    /**
     * 清空StringBuffer对象
     * 
     * @return this
     */
    public LambdaStringBuffer clean() {
        buffer.delete(0, buffer.length());
        return this;
    }

    public LambdaStringBuffer reverse() {
        buffer.reverse();
        return this;
    }

    /**
     * 转为小写
     * 
     * @return this
     */
    public LambdaStringBuffer toLowerCase() {
        String tempStr = buffer.toString().toLowerCase();
        buffer.insert(0, tempStr);
        return this;
    }

    /**
     * 转为大写
     * 
     * @return this
     */
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

    public int compareTo(StringBuffer arg) {
        return buffer.compareTo(arg);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }

}
