package top.youlanqiang.toolbox.resource.json;

import java.io.IOException;
import java.io.Writer;

import top.youlanqiang.toolbox.base.AbstractLambdaStringBuilder;
import top.youlanqiang.toolbox.base.LambdaStringBuilder;

/**
 * Json对象转Json字符串
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

    public JsonWriter(JsonObject object) {
        this.object = object;
        this.type = 0;
    }

    public JsonWriter(JsonArray array) {
        this.array = array;
        this.type = 1;
    }

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
