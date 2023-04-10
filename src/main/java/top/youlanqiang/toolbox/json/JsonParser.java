package top.youlanqiang.toolbox.json;

import java.io.IOException;
import java.io.StringReader;

import top.youlanqiang.toolbox.base.CharReader;
import top.youlanqiang.toolbox.base.ListReader;
import top.youlanqiang.toolbox.json.JsonTokenizer.JsonToken;

/**
 * JsonTokenizer json语法分析器
 * 
 * @author youlanqiang
 */
public class JsonParser {

    private ListReader<JsonToken> tokens;

    /**
     * JsonParser
     * 
     * @param tokens 词法列表
     */
    public JsonParser(ListReader<JsonToken> tokens) {
        this.tokens = tokens;
    }

    /**
     * JsonParser
     * 
     * @param jsonStr json字符串
     * @throws IOException json解析失败
     */
    public JsonParser(String jsonStr) throws IOException {
        this(JsonTokenizer.tokenize(new CharReader(new StringReader(jsonStr))));
    }

    /**
     * 将tokens词法转换为JsonArray
     * 
     * @return JsonArray
     */
    public JsonArray parseJsonArray() {
        JsonArray jsonArray = new JsonArray();
        if (tokens == null || tokens.isEmpty()) {
            return jsonArray;
        }
        if (tokens.next().type() != JsonTokenType.BEGIN_ARR) {
            throw new JsonParseException("Unexpected Token.");
        }
        int expectToken = JsonTokenType.STR.getTokenCode() | JsonTokenType.END_ARR.getTokenCode();

        JsonToken token = null;
        JsonTokenType tokenType = null;
        String tokenValue = null;
        while (tokens.hasMore()) {
            token = tokens.next();
            tokenType = token.type();
            tokenValue = token.value();
            switch (tokenType) {

                case BEGIN_OBJ:
                    checkExpectToken(tokenType, expectToken);
                    tokens.back();
                    jsonArray.add(parseJsonObject());
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_ARR.getTokenCode();
                    break;

                case BEGIN_ARR:
                    checkExpectToken(tokenType, expectToken);
                    tokens.back();
                    jsonArray.add(parseJsonArray());
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_ARR.getTokenCode();
                    break;

                case NULL:
                    checkExpectToken(tokenType, expectToken);
                    jsonArray.add(null);
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_ARR.getTokenCode();
                    break;

                case NUMBER:
                    checkExpectToken(tokenType, expectToken);
                    if (tokenValue.contains(".") || tokenValue.contains("e") || tokenValue.contains("E")) {

                        jsonArray.add(Double.valueOf(tokenValue));
                    } else {
                        Long num = Long.valueOf(tokenValue);
                        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
                            jsonArray.add(num);
                        } else {
                            jsonArray.add(num.intValue());
                        }
                    }
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_ARR.getTokenCode();
                    break;

                case BOOLEAN:
                    checkExpectToken(tokenType, expectToken);
                    jsonArray.add(Boolean.valueOf(tokenValue));
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_ARR.getTokenCode();
                    break;

                case STR:
                    checkExpectToken(tokenType, expectToken);
                    jsonArray.add(tokenValue);
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_ARR.getTokenCode();
                    break;

                case SEP_COMMA:
                    checkExpectToken(tokenType, expectToken);
                    expectToken = JsonTokenType.NULL.getTokenCode() | JsonTokenType.NUMBER.getTokenCode() |
                            JsonTokenType.BOOLEAN.getTokenCode() | JsonTokenType.STR.getTokenCode() |
                            JsonTokenType.BEGIN_OBJ.getTokenCode() | JsonTokenType.BEGIN_ARR.getTokenCode();
                    break;

                case END_DOC:
                    checkExpectToken(tokenType, expectToken);
                    return jsonArray;

                default:
                    throw new JsonParseException("Unexpected Token.");
            }

        }

        throw new JsonParseException("Parse error, invalid Token.");

    }

    /**
     * 将tokens词法转换为JsonObject
     * 
     * @return JsonObject对象
     */
    public JsonObject parseJsonObject() {
        JsonObject jsonObject = new JsonObject();
        if (tokens == null || tokens.isEmpty()) {
            return jsonObject;
        }
        if (tokens.next().type() != JsonTokenType.BEGIN_OBJ) {
            throw new JsonParseException("Unexpected Token.");
        }

        int expectToken = JsonTokenType.STR.getTokenCode() | JsonTokenType.END_OBJ.getTokenCode();

        String key = null;
        JsonToken token = null;
        JsonToken preToken = null;
        JsonTokenType tokenType = null;
        String tokenValue = null;

        while (tokens.hasMore()) {
            preToken = tokens.peekPrevious();
            token = tokens.next();
            tokenType = token.type();
            tokenValue = token.value();

            switch (tokenType) {

                case BEGIN_OBJ:
                    checkExpectToken(tokenType, expectToken);
                    tokens.back();
                    jsonObject.put(key, parseJsonObject());
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_OBJ.getTokenCode();
                    break;

                case END_OBJ:
                    checkExpectToken(tokenType, expectToken);
                    return jsonObject;

                case BEGIN_ARR:
                    checkExpectToken(tokenType, expectToken);
                    tokens.back();
                    jsonObject.put(key, parseJsonArray());
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_OBJ.getTokenCode();
                    break;

                case NULL:
                    checkExpectToken(tokenType, expectToken);
                    jsonObject.put(key, null);
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_OBJ.getTokenCode();
                    break;

                case NUMBER:
                    checkExpectToken(tokenType, expectToken);
                    if (tokenValue.contains(".") || tokenValue.contains("e") || tokenValue.contains("E")) {
                        jsonObject.put(key, Double.valueOf(tokenValue));
                    } else {
                        Long num = Long.valueOf(tokenValue);
                        if (num > Integer.MAX_VALUE || num < Integer.MIN_VALUE) {
                            jsonObject.put(key, num);
                        } else {
                            jsonObject.put(key, num.intValue());
                        }
                    }
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_OBJ.getTokenCode();
                    break;

                case BOOLEAN:
                    checkExpectToken(tokenType, expectToken);
                    jsonObject.put(key, Boolean.valueOf(tokenValue));
                    expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_OBJ.getTokenCode();
                    break;

                case STR:
                    checkExpectToken(tokenType, expectToken);
                    if (preToken != null && preToken.type() == JsonTokenType.SEP_COLON) {
                        // 该字符串是json中的value
                        jsonObject.put(key, tokenValue);
                        expectToken = JsonTokenType.SEP_COMMA.getTokenCode() | JsonTokenType.END_OBJ.getTokenCode();
                        break;
                    } else {
                        // 该字符串是json中的key
                        key = tokenValue;
                        expectToken = JsonTokenType.SEP_COLON.getTokenCode();
                    }

                    break;

                case SEP_COLON:
                    checkExpectToken(tokenType, expectToken);
                    expectToken = JsonTokenType.NULL.getTokenCode() | JsonTokenType.NUMBER.getTokenCode() |
                            JsonTokenType.BOOLEAN.getTokenCode() | JsonTokenType.STR.getTokenCode() |
                            JsonTokenType.BEGIN_OBJ.getTokenCode() | JsonTokenType.BEGIN_ARR.getTokenCode();
                    break;

                case SEP_COMMA:

                    checkExpectToken(tokenType, expectToken);
                    expectToken = JsonTokenType.STR.getTokenCode();
                    break;

                case END_DOC:
                    checkExpectToken(tokenType, expectToken);
                    return jsonObject;

                default:
                    throw new JsonParseException("Unexpected Token.");
            }

        }

        throw new JsonParseException("Parse error, invalid Token.");
    }

    private void checkExpectToken(JsonTokenType tokenType, int expectToken) {

        if ((tokenType.getTokenCode() & expectToken) == 0) {
            throw new JsonParseException("Parse error, invalid Token.");
        }

    }
}
