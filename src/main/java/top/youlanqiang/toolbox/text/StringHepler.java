package top.youlanqiang.toolbox.text;

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
public final class StringHepler {

	private StringHepler() {
	}

	/**
	 * 字符串首位改小写
	 * 
	 * @param str 字符串
	 * @return 首字符转小写
	 */
	public static String lowerFirst(String str) {
		char[] cs = str.toCharArray();
		cs[0] += 32;
		return String.valueOf(cs);
	}

	/**
	 * 判断字符串是否为null
	 * 字符串为"null",也会返回true
	 * 
	 * @param str 判断对象
	 * @return true or false
	 */
	public static boolean isNullString(CharSequence str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		if (str.toString().trim().equalsIgnoreCase("null")) {
			return true;
		}
		return false;

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
	 * 判断字符是否是数字,字符1的ascii码是49
	 * 
	 * @param ch 字符
	 * @return 是数字，则返回true
	 */
	public static boolean isDigit(Character ch) {
		return '0' <= ch && ch <= '9';
	}

	/**
	 * 判断字符是否在十六进制范围
	 * 
	 * @param ch 字符
	 * @return 是十六进制范围字符，则返回true
	 */
	public static boolean isHex(Character ch) {
		return ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'f') || ('A' <= ch && ch <= 'F');
	}

	private static final GenericTokenParser TOKEN_PARSER = new GenericTokenParser("{", "}");

	/**
	 * 使用默认的占位符格式化字符串内容
	 * 
	 * @param text 格式化字符串
	 * @param args 参数列表
	 * @return 格式化后的格式化字符串
	 */
	public static String format(String text, Object... args) {
		return TOKEN_PARSER.parse(text, TokenHandlers.ArrayTokenHandler(args));
	}

	/**
	 * 去掉头尾指定的字符串
	 * 
	 * @param str      字符串
	 * @param splitter 指定字符串
	 * @return 去掉头尾中包含指定字符串后的字符串
	 */
	public static String trimBothEndsChars(String str, String splitter) {
		String regex = "^" + splitter + "*|" + splitter + "*$";
		return str.replaceAll(regex, "");
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
