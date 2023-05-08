package top.youlanqiang.toolbox.json;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;

import top.youlanqiang.toolbox.base.AbstractLambdaStringBuilder;
import top.youlanqiang.toolbox.base.LambdaStringBuilder;
import top.youlanqiang.toolbox.base.ObjectHepler;
import top.youlanqiang.toolbox.base.ReflectHepler;

/**
 * Json对象转Json字符串
 * todo 这个类的功能未实现
 * 
 * @author youlanqiang
 */
public class JsonWriter {

    private AbstractLambdaStringBuilder stringBuilder = new LambdaStringBuilder();

    /**
     * Json数组写入
     * 
     * @param array json数组
     */
    public JsonWriter() {
    }

    public void flush() {
        this.stringBuilder.clean();
    }

    /**
     * 将json字符串写入Writer对象
     * 
     * @param writer writer对象
     * @throws IOException 写入异常
     */
    public synchronized void write(Object object, Writer writer) throws IOException {
        if (object == null) {
            throw new IOException("json target is null.");
        }
        writeObjectString(object);
        writer.close();
    }

    private void writeObjectString(Object object) {
        if (object instanceof Map map) {
            writeJsonObjectString(map);
        } else if (object instanceof Collection list) {
            writeJsonArrayString(list);
        } else {
            writeJavaObjectString(object);
        }
    }

    private void writeJsonObjectString(Map<?, ?> map) {
        stringBuilder.append("{");

        map.forEach((k, v) -> {
            stringBuilder.append("\"", k.toString(), "\"").append(":");
            writeObjectString(v);
            stringBuilder.append(",");
        });

        // 删除最后一个,分号
        stringBuilder.deleteLastChar().append("}");
    }

    private void writeJsonArrayString(Collection<?> jsonArray) {
        stringBuilder.append("[");
        jsonArray.forEach(item -> {
            writeObjectString(item);
            stringBuilder.append(",");
        });
        // 删除最后一个,分号
        stringBuilder.deleteLastChar().append("]");
    }

    private void writeJavaObjectString(Object object) {
        if (object == null) {
            stringBuilder.append("null");
        }
        if (object instanceof CharSequence charSequence) {
            // 表示是字符串类型
            stringBuilder.append(charSequence.toString());
        } else if (object.getClass().isPrimitive()) {
            // 表示是java基本类型直接toString即可
            stringBuilder.append(object.toString());
        } else {
            // 使用反射对象
            var map = ReflectHepler.getPublicGetterMap(object);
            if (ObjectHepler.isEmpty(map)) {
                stringBuilder.append(object.toString());
            } else {
                writeJsonObjectString(map);
            }
        }
    }

}
