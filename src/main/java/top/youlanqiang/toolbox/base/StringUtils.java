package top.youlanqiang.toolbox.base;

import java.util.Collection;

/**
 * 字符串相关工具方法
 */
public final class StringUtils {

    /**
     * 判断字符串是否存在list集合中
     * 
     * @param str  目标字符串
     * @param list 集合
     * @return 忽略大小写进行比较
     */
    public static boolean containIgnoreCase(String str, Collection<String> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        for (String item : list) {
            if (item.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否存在list集合中
     * 
     * @param str   目标字符串
     * @param array 数组
     * @return 忽略大小写进行比较
     */
    public static boolean containIgnoreCase(String str, String... array) {
        if (CollectionUtils.isEmpty(array)) {
            return false;
        }

        for (String item : array) {
            if (item.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断str是否存在长度
     * 
     * @param str 目标字符串
     * @return true of false
     */
    public static boolean hasLength(CharSequence str) {
        return str != null && !str.isEmpty();
    }

    /**
     * 判断str字符串是否存在字符
     * 
     * @param str 目标字符串
     * @return true of false
     */
    public static boolean hasText(CharSequence str) {
        if (str != null && !str.isEmpty()) {
            for (int i = 0; i < str.length(); i++) {
                // 存在一个非空字符串则返回true
                if (!isWhiteSpace(str.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断str是否为空
     * 
     * @param str 目标字符串
     * @return true or false
     */
    public static boolean isEmpty(CharSequence str) {
        return !hasLength(str);
    }

    /**
     * 判断字符是否是空白符
     * 
     * @param ch 字符
     * @return 是空白符，则返回true
     */
    public static boolean isWhiteSpace(Character ch) {
        return (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n');
    }

    /**
     * 判断字符是否是数字
     * 
     * @param ch 字符
     * @return 是数字，则返回true
     */
    public static boolean isDigit(Character ch) {
        return 48 <= ch && ch <= 57;
    }

    /**
     * 判断字符串是否是数字
     * 
     * @param str 字符串
     * @return 如果是数字，则返回true
     */
    public static boolean isNumber(String str) {
        if (!hasLength(str)) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 格式化字符串内容
     * 
     * @param pattern 字符串模版
     * @param args    参数列表
     * @return 格式化后的字符串
     */
    public static String format(String pattern, Object... args) {
        if (isEmpty(pattern) || args == null || args.length == 0) {
            return pattern;
        }
        final int strPatternLength = pattern.length();
        final int placeHolderLength = 2;

        // 初始化定义好的长度以获得更好的性能
        final StringBuilder sbuf = new StringBuilder(strPatternLength + 50);

        int handledPosition = 0;// 记录已经处理到的位置
        int delimIndex;// 占位符所在位置
        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            delimIndex = pattern.indexOf("{}", handledPosition);
            if (delimIndex == -1) {// 剩余部分无占位符
                if (handledPosition == 0) { // 不带占位符的模板直接返回
                    return pattern;
                }
                // 字符串模板剩余部分不再包含占位符，加入剩余部分后返回结果
                sbuf.append(pattern, handledPosition, strPatternLength);
                return sbuf.toString();
            }

            // 转义符
            if (delimIndex > 0 && pattern.charAt(delimIndex - 1) == '\\') {// 转义符
                if (delimIndex > 1 && pattern.charAt(delimIndex - 2) == '\\') {// 双转义符
                    // 转义符之前还有一个转义符，占位符依旧有效
                    sbuf.append(pattern, handledPosition, delimIndex - 1);
                    sbuf.append(args[argIndex]);
                    handledPosition = delimIndex + placeHolderLength;
                } else {
                    // 占位符被转义
                    argIndex--;
                    sbuf.append(pattern, handledPosition, delimIndex - 1);
                    sbuf.append('{');
                    handledPosition = delimIndex + 1;
                }
            } else {// 正常占位符
                sbuf.append(pattern, handledPosition, delimIndex);
                sbuf.append(args[argIndex]);
                handledPosition = delimIndex + placeHolderLength;
            }
        }

        // 加入最后一个占位符后所有的字符
        sbuf.append(pattern, handledPosition, strPatternLength);

        return sbuf.toString();
    }

    /**
     * 直接打印格式化后的字符串
     * 
     * @param pattern 字符串模版
     * @param args    参数列表
     */
    public static void println(String pattern, Object... args) {
        System.out.println(format(pattern, args));
    }

    private StringUtils() {
    }

}
