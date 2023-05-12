package top.youlanqiang.toolbox.base;

import java.util.Collection;
import java.util.Map;

/**
 * 流式字符串构造器，在JDK提供的AbstractStringBuilder基础上提供了更多的操作
 * {@link LambdaStringBuffer} 基于线程安全的StringBuffer实现类
 * {@link LambdaStringBuilder}
 * 基于线程不安全的StringBuilder实现，单线程下性能比StringBuffer快
 * 
 * @author youlanqiang
 *         created in 2022/12/15 23:2
 * 
 */
public interface AbstractLambdaStringBuilder extends CharSequence {

    /**
     * 添加obj对象字符串，会判断是collection还是map类型，会执行对应的字符串化操作
     * 
     * @param obj 添加对象
     * @return this
     */
    AbstractLambdaStringBuilder append(Object obj);

    /**
     * 添加obj数组，会判断是collection还是map类型，会执行对应的字符串化操作
     * 
     * @param objs obj数组对象
     * @return this
     */
    AbstractLambdaStringBuilder append(Object... objs);

    /**
     * 带判断添加obj对象字符串，会判断是collection还是map类型，会执行对应的字符串化操作
     * 
     * @param condition true才会执行操作,false不会执行操作
     * @param obj       添加对象
     * @return this
     */
    AbstractLambdaStringBuilder append(boolean condition, Object obj);

    /**
     * 带判断添加obj数组，会判断是collection还是map类型，会执行对应的字符串化操作
     * 
     * @param condition true才会执行操作,false不会执行操作
     * @param objs      obj数组对象
     * @return this
     */
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
     * keyValueSeparator为=
     * separator为,
     * 
     * @param map map对象
     * @return this
     */
    AbstractLambdaStringBuilder append(Map<?, ?> map);

    /**
     * 循环添加Map中的对象
     * 
     * @param map               map对象
     * @param separator         entry分割符号
     * @param keyValueSeparator kv分割符号
     * @return this
     */
    AbstractLambdaStringBuilder append(Map<?, ?> map, String keyValueSeparator, String separator);

    /**
     * 设置下标处字符
     * 
     * @param index 下标
     * @param ch    字符
     * @return this
     */
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
     * @return this
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

    /**
     * 反转字符
     * 
     * @return this
     */
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

    /**
     * 返回字符串长度
     * 
     * @return 字符串长度
     */
    int length();

    /**
     * 返回buffer容量
     * 
     * @return buffer容量
     */
    int capacity();

    /**
     * 获取下标处字符
     * 
     * @param index 下标
     * @return 字符
     */
    char charAt(int index);

    String toString();

}
