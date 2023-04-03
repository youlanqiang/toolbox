package top.youlanqiang.toolbox.json;

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

        throw new JsonParseException("Illegal character.");
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
     * 判断字符是否为e,表示这串字符是科学计数
     * 
     * @param ch 字符
     * @return 为e返回true
     */
    private boolean isExp(Character ch) {
        return ch == 'e' || ch == 'E';
    }

    /**
     * 从CharReader中读取null
     * 
     * @param charReader 字符串流
     * @return JsonToken,类型为null
     */
    private JsonToken readNull(CharReader charReader) throws IOException {
        // 使用&&进行判断会触发短路,使charReader停留在u字符上
        if (charReader.next() != 'u' || charReader.next() != 'l' || charReader.next() != 'l') {
            throw new JsonParseException("Invalid json string.");
        }

        return new JsonToken(JsonTokenType.NULL, "null");
    }

    /**
     * 从CharReader中读取true or false
     * 
     * @param charReader 字符串流
     * @return JsonToken,类型为boolean
     */
    private JsonToken readBoolean(CharReader charReader) throws IOException {
        if (charReader.peek() == 't') {
            // charReader中读取到的字符是 true
            if (charReader.next() == 'r' && charReader.next() == 'u' && charReader.next() == 'e') {
                return new JsonToken(JsonTokenType.BOOLEAN, "true");
            }
        } else {
            // charReader中读取到的字符是 false
            if (charReader.next() == 'a' && charReader.next() == 'l' && charReader.next() == 's'
                    && charReader.next() == 'e') {
                return new JsonToken(JsonTokenType.BOOLEAN, "false");
            }
        }

        throw new JsonParseException("Invalid json string.");
    }

    /**
     * 从CharReader中读取字符串
     * 
     * @param charReader 字符串流
     * @return JsonToken,类型为string
     */
    private JsonToken readString(CharReader charReader) throws IOException {
        StringBuffer sb = new StringBuffer();

        for (;;) {
            char ch = charReader.next();

            // 读取到转义符号
            if (ch == '\\') {
                if (!isEscape(charReader)) {
                    throw new JsonParseException("Invalid escape character.");
                }
                sb.append('\\');
                ch = charReader.peek();
                sb.append(ch);

                // 表示这是unicode编码 如: \\u000a 这种
                if (ch == 'u') {
                    for (int i = 0; i < 4; i++) {
                        ch = charReader.next();
                        if (ObjectHepler.isHex(ch)) {
                            sb.append(ch);
                        } else {
                            throw new JsonParseException("Invalid character.");
                        }
                    }
                }

            } else if (ch == '"') {
                // 字符串的结束
                return new JsonToken(JsonTokenType.STR, sb.toString());

            } else if (ch == '\r' || ch == '\n') {
                // \\r 是回车 \\n 是换行
                throw new JsonParseException("Invalid character.");
            } else {
                sb.append(ch);
            }
        }

    }

    /**
     * 从CharReader中读取数字
     * 
     * @param charReader 字符串流
     * @return JsonToken,类型为number
     */
    private JsonToken readNumber(CharReader charReader) throws IOException {

        char ch = charReader.peek();
        StringBuilder sb = new StringBuilder();
        if (ch == '-') {
            // 处理负数
            sb.append(ch);
            ch = charReader.next();
            if (ch == '0') {
                sb.append(ch);
                sb.append(readFracAndExp(charReader));
            } else if (ObjectHepler.isDigit(ch)) {
                do {
                    sb.append(ch);
                    ch = charReader.next();
                } while (ObjectHepler.isDigit(ch));
                if (ch != -1) {
                    charReader.back();
                    // 读取科学计数法，如果不是科学计数法，这步操作什么也不会做
                    sb.append(readFracAndExp(charReader));
                }
            } else {
                throw new JsonParseException("Invalid numbers.");
            }
        } else if (ch == '0') {
            // 处理小数
            sb.append(ch);
            sb.append(readFracAndExp(charReader));
        } else {
            do {
                sb.append(ch);
                ch = charReader.next();
            } while (ObjectHepler.isDigit(ch));

            if (ch != -1) {
                charReader.back();
                // 读取科学计数法，如果不是科学计数法，这步操作什么也不会做
                sb.append(readFracAndExp(charReader));
            }
        }

        return new JsonToken(JsonTokenType.NUMBER, sb.toString());
    }

    /**
     * 读取浮点数字符串
     * 
     * @param charReader 字符串流
     * @return
     * @throws IOException
     */
    public String readFracAndExp(CharReader charReader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char ch = charReader.next();
        if (ch == '.') {
            sb.append(ch);
            ch = charReader.next();

            if (!ObjectHepler.isDigit(ch)) {
                throw new JsonParseException("Invalid frac.");
            }

            do {
                sb.append(ch);
                ch = charReader.next();
            } while (ObjectHepler.isDigit(ch));

            // 处理科学计数法，如 1.022E5 这种情况
            if (isExp(ch)) {
                sb.append(ch);
                sb.append(readExp(charReader));
            } else {

                // 如果不是charReader读取结束，即返回-1的情况。
                // 则charReader回退1位,因为exp读取结束会读取到 , } 这些字符,词法解析还得继续。
                if (ch != -1) {
                    charReader.back();
                }
            }

        } else if (isExp(ch)) {
            sb.append(ch);
            sb.append(readExp(charReader));
        } else {
            charReader.back();
        }

        return sb.toString();
    }

    /**
     * 处理科学计数法
     * 
     * @param charReader 字符串流
     * @return
     * @throws IOException
     */
    public String readExp(CharReader charReader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char ch = charReader.next();
        if (ch == '+' || ch == '-') {
            sb.append(ch);
            ch = charReader.next();
            if (ObjectHepler.isDigit(ch)) {

                do {
                    sb.append(ch);
                    ch = charReader.next();
                } while (ObjectHepler.isDigit(ch));

                // 如果不是charReader读取结束，即返回-1的情况。
                // 则charReader回退1位,因为exp读取结束会读取到 , } 这些字符,词法解析还得继续。
                if (ch != -1) {
                    charReader.back();
                }

            } else {
                throw new JsonParseException("Invalid exp.");
            }
        } else if (ObjectHepler.isDigit(ch)) {
            do {
                sb.append(ch);
                ch = charReader.next();
            } while (ObjectHepler.isDigit(ch));

            // 如果不是charReader读取结束，即返回-1的情况。
            // 则charReader回退1位,因为exp读取结束会读取到 , } 这些字符,词法解析还得继续。
            if (ch != -1) {
                charReader.back();
            }
        } else {
            throw new JsonParseException("Invalid exp.");
        }
        return sb.toString();
    }

}
