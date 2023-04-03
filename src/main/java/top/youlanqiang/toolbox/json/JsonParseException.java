package top.youlanqiang.toolbox.json;

/**
 * JsonParse失败异常
 * 
 * @author youlanqiang
 */
public class JsonParseException extends RuntimeException {

    public JsonParseException() {
        super();
    }

    public JsonParseException(String msg) {
        super(msg);
    }
}
