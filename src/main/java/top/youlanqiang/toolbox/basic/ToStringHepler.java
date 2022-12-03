package top.youlanqiang.toolbox.basic;

import top.youlanqiang.toolbox.Toolbox;

/**
 * @author youlanqiang
 * created in 2022/12/03 09:56
 * 字符串工具类
 */
public class ToStringHepler {

    /**
     * 格式化字符串内容
     * @param pattern 字符串模版
     * @param arg 参数列表
     * @return 结果
     */
    public static String format(String pattern, Object... args){
        if(Toolbox.isEmpty(pattern) || Toolbox.isEmpty(args)){
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
    
}
