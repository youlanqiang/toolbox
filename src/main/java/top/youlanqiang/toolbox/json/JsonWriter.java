package top.youlanqiang.toolbox.json;

import java.io.IOException;
import java.time.temporal.Temporal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

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

    public static final JsonWriter INSTANCE = new JsonWriter();

    private AbstractLambdaStringBuilder stringBuilder = new LambdaStringBuilder();

    /**
     * Json数组写入
     * 
     * @param array json数组
     */
    private JsonWriter() {
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
    public synchronized String writeToString(Object object) {
        if (object == null) {
            return null;
        }
        writeObjectString(object);
        String result = stringBuilder.toString();
        flush();
        return result;
    }

    private void writeObjectString(Object object) {
        if (object instanceof Map map) {
            writeJsonObjectString(map);
        } else if (object instanceof Collection<?> list) {
            writeJsonArrayStringByCollection(list);
        } else {
            writeJavaObjectString(object);
        }
    }

    private void writeJsonObjectString(Map<?, ?> map) {
        stringBuilder.append("{");

        if (!map.isEmpty()) {
            map.forEach((k, v) -> {
                stringBuilder.append("\"", k.toString(), "\"").append(":");
                writeObjectString(v);
                stringBuilder.append(",");
            });
            // 删除最后一个,分号
            stringBuilder.deleteLastChar();
        }

        stringBuilder.append("}");
    }

    private <T> void writeJsonArrayStringByCollection(Iterable<T> array) {
        stringBuilder.append("[");

        int mod = 0;
        for (T item : array) {
            writeObjectString(item);
            stringBuilder.append(",");
            mod++;
        }

        if (mod != 0) {
            // 删除最后一个,分号
            stringBuilder.deleteLastChar();
        }

        stringBuilder.append("]");
    }

    private void writeJsonArrayStringByArray(Object[] array) {
        stringBuilder.append("[");

        int mod = 0;
        for (Object item : array) {
            writeObjectString(item);
            stringBuilder.append(",");
            mod++;
        }

        if (mod != 0) {
            // 删除最后一个,分号
            stringBuilder.deleteLastChar();
        }

        stringBuilder.append("]");
    }

    private void writeJavaObjectString(Object object) {
        if (object == null) {
            stringBuilder.append("null");
        }
        if (object instanceof CharSequence charSequence) {
            // 表示是字符串类型
            stringBuilder.append("\"").append(charSequence.toString()).append("\"");
        } else if (object.getClass().isArray()) {

            writeJsonArrayStringByArray((Object[]) object);
        } else if (object.getClass().isPrimitive()) {
            // 表示是java基本类型直接toString即可
            stringBuilder.append(object.toString());
        } else if (object instanceof Boolean bool) {
            stringBuilder.append(bool.toString());
        } else if (object instanceof Number number) {
            stringBuilder.append(number);
        } else if (object instanceof Character character) {
            stringBuilder.append("\"").append(character).append("\"");
        } else if (object instanceof Optional optional) {
            writeJavaObjectString(optional.get());
        } else if (object instanceof Date date) {
            stringBuilder.append("\"").append(date.toString()).append("\"");
        } else if (object instanceof Temporal temporal) {
            stringBuilder.append("\"").append(temporal.toString()).append("\"");
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
