package top.youlanqiang.toolbox.json;

/**
 * 词法token的类型
 * 
 * @author youlanqiang
 */
public enum JsonTokenType {

    // {
    BEGIN_OBJ(1, "{"),

    // }
    END_OBJ(2, "}"),

    // [
    BEGIN_ARR(4, "["),

    // ]
    END_ARR(8, "]"),

    // n
    NULL(16, "null"),

    // 0-9
    NUMBER(32, "0"),

    // "
    STR(64, "\""),

    // t or f
    BOOLEAN(128, "false"),

    // :
    SEP_COLON(256, ":"),

    // ,
    SEP_COMMA(512, ","),

    // 结束
    END_DOC(1024, "end");

    private Integer code;

    private String token;

    JsonTokenType(Integer code, String token) {
        this.code = code;
        this.token = token;
    }

    public int getTokenCode() {
        return code;
    }

    public String getToken() {
        return token;
    }

}
