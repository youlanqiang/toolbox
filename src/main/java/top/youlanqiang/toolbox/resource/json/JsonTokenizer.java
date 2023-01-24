package top.youlanqiang.toolbox.resource.json;

import java.io.IOException;

import top.youlanqiang.toolbox.base.CharReader;
import top.youlanqiang.toolbox.base.ListReader;
import top.youlanqiang.toolbox.base.ObjectHepler;

/**
 * @author youlanqiang
 */
public class JsonTokenizer {

    public static final JsonTokenizer INSTANCE = new JsonTokenizer();

    public record JsonToken(JsonTokenType type, String value) {
    };

    private JsonTokenizer() {
    }

    /**
     * 将json字符串词法分析转换为Token流
     * 
     * @param charReader json字符串流
     * @return json的token词法流
     * @throws IOException json解析失败的io异常
     */
    public static ListReader<JsonToken> tokenize(final CharReader charReader) throws IOException {
        ListReader<JsonToken> tokens = ListReader.asArrayList();
        JsonToken token;
        do {
            token = INSTANCE.parseToken(charReader);
            tokens.add(token);
        } while (token.type() != JsonTokenType.END_DOC);
        return tokens;
    }

    private JsonToken parseToken(final CharReader charReader) throws IOException {
        char ch;

        for (;;) {
            if (!charReader.hasMore()) {
                return new JsonToken(JsonTokenType.END_DOC, null);
            }

            ch = charReader.next();

            // 忽略空字符
            if (!ObjectHepler.isWhiteSpace(ch)) {
                break;
            }

        }

        switch (ch) {
            case '{':
                return new JsonToken(JsonTokenType.BEGIN_OBJ, "{");
            case '}':
                return new JsonToken(JsonTokenType.END_OBJ, "}");
            case '[':
                return new JsonToken(JsonTokenType.BEGIN_ARR, "[");
            case ']':
                return new JsonToken(JsonTokenType.END_ARR, "]");
            case ':':
                return new JsonToken(JsonTokenType.SEP_COLON, ":");
            case ',':
                return new JsonToken(JsonTokenType.SEP_COMMA, ",");
            // 读取null token
            case 'n':
                return readNull(charReader);
            // 读取boolean token
            case 't':
            case 'f':
                return readBoolean(charReader);
            // 读取string token
            case '"':
                return readString(charReader);
            // 读取 number token,因为这是个负数
            case '-':
                return readNumber(charReader);
        }

        // 判断字符是否为0-9,如果是读取数字token
        if (ObjectHepler.isDigit(ch)) {
            return readNumber(charReader);
        }

        throw new JsonParseException("Illegal character");
    }

    /**
     * 判断下一个字符是否是合法的转义字符
     * 
     * @param charReader
     * @return
     * @throws IOException
     */
    private boolean isEscape(CharReader charReader) throws IOException {
        char ch = charReader.next();
        return (ch == '"' || ch == '\\' || ch == 'u' || ch == 'r'
                || ch == 'n' || ch == 'b' || ch == 't' || ch == 'f');
    }

    /**
     * 从CharReader中读取null
     * 
     * @param charReader 字符串流
     * @return JsonToken,类型为null
     */
    private JsonToken readNull(CharReader charReader) {
        return null;
    }

    /**
     * 从CharReader中读取true or false
     * 
     * @param charReader 字符串流
     * @return JsonToken,类型为boolean
     */
    private JsonToken readBoolean(CharReader charReader) {
        return null;
    }

    /**
     * 从CharReader中读取字符串
     * 
     * @param charReader 字符串流
     * @return JsonToken,类型为string
     */
    private JsonToken readString(CharReader charReader) {
        return null;
    }

    /**
     * 从CharReader中读取数字
     * 
     * @param charReader 字符串流
     * @return JsonToken,类型为number
     */
    private JsonToken readNumber(CharReader charReader) {
        return null;
    }

}
