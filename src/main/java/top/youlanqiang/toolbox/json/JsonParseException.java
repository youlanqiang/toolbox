package top.youlanqiang.toolbox.json;

/**
 * JsonParse失败异常
 * 
 * @author youlanqiang
 */
public class JsonParseException extends RuntimeException {

    /**
     * JsonParse异常
     */
    public JsonParseException() {
        super();
    }

    /**
     * 带有异常信息的JsonParse异常
     * 
     * @param msg 异常信息
     */
    public JsonParseException(String msg) {
        super(msg);
    }
}
