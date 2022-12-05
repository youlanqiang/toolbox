package top.youlanqiang.toolbox;

import java.util.Collection;
import java.util.Map;

import top.youlanqiang.toolbox.basic.EqualsHepler;
import top.youlanqiang.toolbox.basic.ToStringHepler;

/**
 * @author youlanqiang
 * created in 2022/12/03 09:30
 * 常用工具类
 */
public final class Toolbox {

    private Toolbox(){}

    /**
     * 创建一个比较器
     * @see top.youlanqiang.toolbox.basic.EqualseHepler
     * @return 
     */
    public static EqualsHepler equalsHepler(){
        return new EqualsHepler();
    }

    /**
     * 快速打印
     * @param str 打印内容
     */
    public static void println(String str){
        System.out.println(str);
    }

    /**
     * 快速打印格式化字符串内容
     * @param str 打印内容
     */
    public static void println(String pattern, Object... args){
        System.out.println(format(pattern, args));
    }

    /**
	 * 使用默认的占位符 {} 格式化字符串内容
	 * @param pattern 字符串模版
	 * @param args 参数列表
	 * @return 格式化后的格式化字符串
	 */
    public static String format(String pattern, Object... arg){
        return ToStringHepler.format(pattern, arg);
    }

    /**
     * 字符串是否为空
     * @param str 字符串
     * @return true or false
     */
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * 数组是否为空
     * @param objects 数组
     * @return true or false
     */
    public static boolean isEmpty(Object... objects){
        return objects == null || objects.length == 0;
    }

    /**
     * Map是否为空
     * @param map map对象
     * @return true or false
     */
    public static boolean isEmpty(Map<Object, Object> map){
        return map == null || map.size() == 0;
    }

    /**
     * 集合是否为空
     * @param collection 集合对象
     * @return true or false
     */
    public static boolean isEmpty(Collection<Object> collection){
        return collection == null || collection.size() == 0;
    }
    
}
