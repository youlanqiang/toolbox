package top.youlanqiang.toolbox.text;

import java.util.ArrayList;
import java.util.Map;

/**
 * TokenHandlers工具类，提供了3种常用的TokenHandler
 * 
 * @author youlanqiang
 */
public final class TokenHandlers {

    /**
     * 支持序列化如 ${property}的占位符号
     * 
     * @param map map对象
     * @return TokenHandler
     */
    public static TokenHandler MapTokenHandler(final Map<String, ?> map) {

        return (token) -> {
            if (map.containsKey(token.getTokenValue())) {
                return map.get(token.getTokenValue()).toString();
            } else if (token.getDefaultValue() != null) {
                return token.getDefaultValue();
            }
            return token.getRaw();
        };
    }

    /**
     * 支持序列化如 {}的占位符号
     * 
     * @param objects 数组
     * @return TokenHandler
     */
    public static TokenHandler ArrayTokenHandler(final Object... objects) {
        return (token) -> {
            Object obj = objects[token.getIndex()];
            if (obj != null) {
                return obj.toString();
            } else if (token.getDefaultValue() != null) {
                return token.getDefaultValue();
            }
            return token.getRaw();
        };
    }

    /**
     * 支持序列化如 {}的占位符号
     * 
     * @param list 列表
     * @return TokenHandler
     */
    public static TokenHandler ListTokenHandler(final ArrayList<?> list) {
        return (token) -> {
            Object obj = list.get(token.getIndex());
            if (obj != null) {
                return obj.toString();
            } else if (token.getDefaultValue() != null) {
                return token.getDefaultValue();
            }
            return token.getRaw();
        };
    }
}
