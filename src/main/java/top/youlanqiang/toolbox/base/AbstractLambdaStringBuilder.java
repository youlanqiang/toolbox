package top.youlanqiang.toolbox.base;

import java.util.Collection;
import java.util.Map;

/**
 * @author youlanqiang
 *         created in 2022/12/15 23:2
 *         流式字符串构造器，在JDK提供的AbstractStringBuilder基础上提供了更多的操作
 *         {@link LambdaStringBuffer} 基于线程安全的StringBuffer实现类
 *         {@link LambdaStringBuilder}
 *         基于线程不安全的StringBuilder实现，单线程下性能比StringBuffer快
 */
interface AbstractLambdaStringBuilder {

    AbstractLambdaStringBuilder append(Object obj);

    AbstractLambdaStringBuilder append(Object... objs);

    AbstractLambdaStringBuilder append(boolean condition, Object obj);

    AbstractLambdaStringBuilder append(boolean condition, Object... objs);

    /**
     * 循环添加Collection中的对象，默认分隔符为空字符串
     * 
     * @param list list对象
     * @return this
     */
    AbstractLambdaStringBuilder append(Collection<?> list);

    /**
     * 循环添加Collection中的对象
     * 
     * @param list      list对象
     * @param separator 指定分隔符
     * @return this
     */
    AbstractLambdaStringBuilder append(Collection<?> list, String separator);

    /**
     * 循环添加Map中的对象
     * 
     * @param map               map对象
     * @param separator         entry分割符号
     * @param keyValueSeparator kv分割符号
     * @return string
     */
    AbstractLambdaStringBuilder append(Map<?, ?> map, String keyValueSeparator, String separator);

    AbstractLambdaStringBuilder setCharAt(int index, char ch);

    /**
     * 给定字符串的重复 count 次的串联
     * 
     * @param count 重复次数
     * @return this
     */
    AbstractLambdaStringBuilder repeat(int count);

    /**
     * 删除开始下标到结束下标的字段
     * 
     * @param start 开始下标
     * @param end   结束下标
     * @return this
     */
    AbstractLambdaStringBuilder delete(int start, int end);

    /**
     * 删除开头第一个字符
     * 
     * @return
     */
    AbstractLambdaStringBuilder deleteFirstChar();

    /**
     * 删除开头size长度的字符串
     * 
     * @param size 长度
     * @return this
     */
    AbstractLambdaStringBuilder deleteFirst(int size);

    /**
     * 删除结尾最后一个字符
     * 
     * @return this
     */
    AbstractLambdaStringBuilder deleteLastChar();

    /**
     * 删除结尾size长度的字符串
     * 
     * @param size 长度
     * @return this
     */
    AbstractLambdaStringBuilder deleteLast(int size);

    /**
     * 清空StringBuffer对象
     * 
     * @return this
     */
    AbstractLambdaStringBuilder clean();

    AbstractLambdaStringBuilder reverse();

    /**
     * 转为小写
     * 
     * @return this
     */
    AbstractLambdaStringBuilder toLowerCase();

    /**
     * 转为大写
     * 
     * @return this
     */
    AbstractLambdaStringBuilder toUpperCase();

    int length();

    int capacity();

    char charAt(int index);

    String toString();

}
