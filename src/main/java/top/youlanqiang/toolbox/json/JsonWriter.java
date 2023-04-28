package top.youlanqiang.toolbox.json;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import top.youlanqiang.toolbox.base.AbstractLambdaStringBuilder;
import top.youlanqiang.toolbox.base.LambdaStringBuilder;

/**
 * Json对象转Json字符串
 * todo 这个类的功能未实现
 * 
 * @author youlanqiang
 */
public class JsonWriter {

    private Object root;

    private AbstractLambdaStringBuilder stringBuilder = new LambdaStringBuilder();

    /**
     * Json数组写入
     * 
     * @param array json数组
     */
    public JsonWriter(Object object) {
        this.root = object;

    }

    /**
     * 将json字符串写入Writer对象
     * 
     * @param writer writer对象
     * @throws IOException 写入异常
     */
    public void write(Writer writer) throws IOException {
        if (root == null) {
            throw new IOException("json target is null.");
        }
        if (root instanceof HashMap<?, ?> map) {
            writeJsonObjectString(map);
        } else if (root instanceof List<?> list) {
            writeJsonArrayString(list);
        } else {
            writeJsonObjectString(root);
        }

        writer.close();
    }

    private void writeJsonObjectString(HashMap<?, ?> object) {
        stringBuilder.append("{")

                .append("}");
    }

    private void writeJsonObjectString(Object object) {
        stringBuilder.append("{")

                .append("}");
    }

    private void writeJsonArrayString(List<?> jsonArray) {
        stringBuilder.append("[")
                .append("]");
    }

}
