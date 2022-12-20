package top.youlanqiang.toolbox.base;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import top.youlanqiang.toolbox.collection.Pair;

/**
 * 
 * 字符串工具类
 * 
 * @author youlanqiang
 *         created in 2022/12/03 09:56
 * 
 */
public final class ToStringHepler {

	private ToStringHepler() {
	}

	/**
	 * 默认占位符
	 */
	public static final String DEFAULT_TAG = "{}";

	/**
	 * 使用默认的占位符格式化字符串内容
	 * 
	 * @param pattern 格式化字符串
	 * @param args    参数列表
	 * @return 格式化后的格式化字符串
	 */
	public static String format(String pattern, Object... args) {
		return format(pattern, DEFAULT_TAG, args);
	}

	/**
	 * 格式化字符串内容
	 * 
	 * @param pattern 格式化字符串
	 * @param holder  占位符标签
	 * @param args    参数列表
	 * @return 格式化后的格式化字符串
	 */
	public static String format(String pattern, String holder, Object... args) {
		if (ObjectHepler.isEmpty(pattern) || ObjectHepler.isEmpty(args)) {
			return pattern;
		}
		final int strPatternLength = pattern.length();
		final int placeHolderLength = holder.length();

		// 初始化定义好的长度以获得更好的性能
		final StringBuilder sbuf = new StringBuilder(strPatternLength + 50);

		int handledPosition = 0;// 记录已经处理到的位置
		int delimIndex;// 占位符所在位置
		for (int argIndex = 0; argIndex < args.length; argIndex++) {
			delimIndex = pattern.indexOf(holder, handledPosition);
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
					sbuf.append(holder.charAt(0));
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
	 * 创建内部类ObjectToStringBuilder，传入obj对象会将对象class的simpleName设置为默认名称
	 * 
	 * @param obj 构造器的默认对象
	 * @return 对象字符串构造器
	 */
	public static ObjectToStringBuilder build(Object obj) {
		return new ObjectToStringBuilder(obj.getClass().getSimpleName());
	}

	/**
	 * 创建内部类ObjectToStringBuilder，传入class的类名
	 * 
	 * @param clazz 类型
	 * @return 对象字符串构造器
	 */
	public static ObjectToStringBuilder build(Class<?> clazz) {
		return new ObjectToStringBuilder(clazz.getSimpleName());
	}

	/**
	 * 创建内部类ObjectToStringBuilder，传入class名称
	 * 
	 * @param className 类型的名称
	 * @return 对象字符串构造器
	 */
	public static ObjectToStringBuilder build(String className) {
		return new ObjectToStringBuilder(className);
	}

	/**
	 * 用于辅助{@link Object#toString}的工具类
	 */
	public static final class ObjectToStringBuilder {

		private final String className;

		private final List<Entry<String, Object>> fieldEntries;

		/**
		 * 对象字符串构造器，生成的字符串会将对象类名作为前缀
		 * 如: Map{k=1,k=2}
		 * 
		 * @param className 对象类名
		 */
		public ObjectToStringBuilder(String className) {
			this.className = className;
			this.fieldEntries = new LinkedList<>();
		}

		/**
		 * 将对象属性和值放入字符串构造器中，调用toString方法会生成
		 * ClassName{key=value}的可读字符串
		 * 
		 * @param key   对象属性
		 * @param value 对象值
		 * @return this
		 */
		public ObjectToStringBuilder put(String key, Object value) {
			this.fieldEntries.add(Pair.of(key, value));
			return this;
		}

		public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append(className).append("{");
			fieldEntries.forEach(entry -> {
				buffer
						.append(entry.getKey())
						.append("=")
						.append(String.valueOf(entry.getValue()))
						.append(",");
			});
			// 删除掉最后一位逗号
			buffer.deleteCharAt(buffer.length() - 1);
			return buffer.append("}").toString();
		}

	}

	/**
	 * 将map转换为字符串
	 * 
	 * @param map               map对象
	 * @param separator         entry分割符号
	 * @param keyValueSeparator kv分割符号
	 * @return string
	 */
	public static String mapToString(Map<?, ?> map, String separator, String keyValueSeparator) {
		StringBuilder builder = new StringBuilder();
		map.forEach((k, v) -> {
			builder
					.append(Objects.toString(k))
					.append(keyValueSeparator)
					.append(Objects.toString(v))
					.append(separator);
		});
		// 删除掉最后一位逗号
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	/**
	 * 将传入的字符串转为可读的字符串对象
	 * 
	 * @param list      集合
	 * @param open      字符串头
	 * @param close     字符串尾
	 * @param separator 分隔符
	 * @return 转换后的字符串
	 */
	public static String listToString(Collection<?> list, String open, String close, String separator) {
		StringBuilder builder = new StringBuilder();
		builder.append(open);
		list.forEach((item) -> {
			builder
					.append(item)
					.append(separator);
		});
		// 删除掉最后一位逗号
		builder.deleteCharAt(builder.length() - 1);
		builder.append(close);
		return builder.toString();
	}

}
