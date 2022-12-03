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
    public static void format(String pattern, Object... arg){
        println(ToStringHepler.format(pattern, arg));
    }

    /**
     * 字符串是否为空
     * @param str 字符串
     * @return true or false
     */
    public static boolean isEmpty(String str){
        if(str == null || str.length() == 0){
            return true;
        }
        return false;
    }

    public static boolean isEmpty(Object... objects){
        if(objects == null || objects.length == 0){
            return true;
        }
        return false;
    }

    /**
     * Map是否为空
     * @param map map对象
     * @return true or false
     */
    public static boolean isEmpty(Map<Object, Object> map){
        if(map == null || map.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * 集合是否为空
     * @param collection 集合对象
     * @return true or false
     */
    public static boolean isEmpty(Collection<Object> collection){
        if(collection == null || collection.size() == 0){
            return true;
        }
        return false;
    }
    
}
