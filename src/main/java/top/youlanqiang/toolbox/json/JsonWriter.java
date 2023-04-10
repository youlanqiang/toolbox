package top.youlanqiang.toolbox.json;

import java.io.IOException;
import java.io.Writer;

import top.youlanqiang.toolbox.base.AbstractLambdaStringBuilder;
import top.youlanqiang.toolbox.base.LambdaStringBuilder;

/**
 * Json对象转Json字符串
 * todo 这个类的功能未实现
 * 
 * @author youlanqiang
 */
public class JsonWriter {

    private JsonObject object;

    private JsonArray array;

    /**
     * json类型 0=JsonObject 1=JsonArray
     */
    private final int type;

    private AbstractLambdaStringBuilder stringBuilder = new LambdaStringBuilder();

    /**
     * Json对象写入
     * 
     * @param object json对象
     */
    public JsonWriter(JsonObject object) {
        this.object = object;
        this.type = 0;
    }

    /**
     * Json数组写入
     * 
     * @param array json数组
     */
    public JsonWriter(JsonArray array) {
        this.array = array;
        this.type = 1;
    }

    /**
     * 将json字符串写入Writer对象
     * 
     * @param writer writer对象
     * @throws IOException 写入异常
     */
    public void write(Writer writer) throws IOException {
        if (type == 0 && object == null) {
            writer.write("{}");
        } else if (type == 1 && array == null) {
            writer.write("[]");
        } else {
            writer.write(toJsonString());
        }

        writer.close();
    }

    private String toJsonString() {
        if (type == 0) {
            writeJsonObjectString(object);
        } else {
            writeJsonArrayString(array);
        }
        return stringBuilder.toString();
    }

    private void writeJsonObjectString(JsonObject object) {
        stringBuilder.append("{")

                .append("}");
    }

    private void writeJsonArrayString(JsonArray jsonArray) {
        stringBuilder.append("[")
                .append("]");
    }

}
