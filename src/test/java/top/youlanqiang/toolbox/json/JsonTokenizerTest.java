package top.youlanqiang.toolbox.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.base.CharReader;
import top.youlanqiang.toolbox.base.ListReader;
import top.youlanqiang.toolbox.json.JsonTokenizer.JsonToken;

@DisplayName("JsonTokenizer测试类")
public class JsonTokenizerTest {

    final String jsonObjectStr = """
            {"str":"hello,toolbox","num":-1.11E10,"bool":false,"null":null}
            """;

    @DisplayName("测试JsonObject反序列化是否正确")
    @Test
    public void testJsonObjectParser() throws IOException {
        JsonParser jsonParser = new JsonParser(jsonObjectStr);
        JsonObject jsonObject = jsonParser.parseJsonObject();
        assertEquals(false, jsonObject.get("bool"));
    }

    @DisplayName("通过判断token数量验证词法分析是否正确")
    @Test
    public void testTokenizer() throws IOException {

        CharReader reader = new CharReader(new StringReader(jsonObjectStr));
        ListReader<JsonToken> tokens = JsonTokenizer.tokenize(reader);
        assertEquals(18, tokens.size());
    }
}
