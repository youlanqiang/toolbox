package top.youlanqiang.toolbox.text;

import top.youlanqiang.toolbox.base.ObjectHepler;

/**
 * 普通记号解析器，处理#{}和${}参数
 * 
 * @author youlanqiang
 */
public class GenericTokenParser {

    /**
     * 开始标记
     */
    private final String openToken;

    /**
     * 结束标记
     */
    private final String closeToken;

    public GenericTokenParser(String openToken, String closeToken) {
        this.openToken = openToken;
        this.closeToken = closeToken;
    }

    /**
     * 解析字符串
     * 
     * @param text         原始文本
     * @param tokenHandler token后续处理器
     * @return token后续处理器处理后的文本
     */
    public String parse(String text, TokenHandler tokenHandler) {
        if (ObjectHepler.isEmpty(text) || text.length() == 0) {
            return null;
        }
        StringBuilder builder = new StringBuilder();
        char[] src = text.toCharArray();
        int offset = 0;
        int start = text.indexOf(openToken, offset);
        int index = 0;

        // 循环解析参数
        while (start > -1) {
            // 判断一下 ${ 前面是否是反斜杠
            if (start > 0 && src[start - 1] == '\\') {
                builder.append(src, offset, start - offset - 1).append(openToken);
                offset = start + openToken.length();
            } else {
                int end = text.indexOf(closeToken, start);
                if (end == -1) {
                    // 将后续的字符串全部填充进builder中
                    builder.append(src, offset, src.length - offset);
                    offset = src.length;
                } else {
                    // offset到opentoken前，也就是普通字符串放入builder中
                    builder.append(src, offset, start - offset);
                    // 下标移动到opentoken后一位
                    offset = start + openToken.length();
                    // 读取opentoken到closetoken中的字符串
                    String content = new String(src, offset, end - offset);
                    builder.append(tokenHandler.handleToken(doParseStringToken(index++, content)));

                    // 移动到closeToken后一位
                    offset = end + closeToken.length();
                }
            }
            // 移动到下一个openToken下标
            start = text.indexOf(openToken, offset);
        }

        // 未解析完的字符串放入builder中
        if (offset < src.length) {
            builder.append(src, offset, src.length - offset);
        }

        return builder.toString();
    }

    private StringToken doParseStringToken(int index, String token) {
        StringToken stringToken = new StringToken(index, token, openToken + token + closeToken);
        int defaultIndex = token.lastIndexOf(":");
        if (defaultIndex == -1) {
            return stringToken;
        }
        String defaultValue = token.substring(defaultIndex);
        stringToken.setDefaultValue(defaultValue);
        String newToken = token.substring(0, defaultIndex + 1);
        stringToken.setTokenValue(newToken);
        return stringToken;
    }
}
