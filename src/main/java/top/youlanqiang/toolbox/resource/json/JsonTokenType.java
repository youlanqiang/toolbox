package top.youlanqiang.toolbox.resource.json;

/**
 * 词法token的类型
 * 
 * @author youlanqiang
 */
enum JsonTokenType {

    // {
    BEGIN_OBJ(1),

    // }
    END_OBJ(2),

    // [
    BEGIN_ARR(4),

    // ]
    END_ARR(8),

    // n
    NULL(16),

    // 0-9
    NUMBER(32),

    // "
    STR(64),

    // t or f
    BOOLEAN(128),

    // :
    SEP_COLON(256),

    // ,
    SEP_COMMA(512),

    // 结束
    END_DOC(1024);

    private Integer code;

    JsonTokenType(Integer code) {
        this.code = code;
    }

    public int getTokenCode() {
        return code;
    }

}
