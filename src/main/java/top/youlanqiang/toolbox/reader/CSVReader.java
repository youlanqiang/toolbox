package top.youlanqiang.toolbox.reader;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import top.youlanqiang.toolbox.base.IOHepler;
import top.youlanqiang.toolbox.text.StringHepler;

/**
 * CSVResource读取辅助类，在不使用时需要调用Close方法，释放流。
 * 
 * @author youlanqiang
 */
public class CSVReader implements Closeable {

    private static final CSVStringColmunCover COLMUN_COVER = new CSVStringColmunCover();

    /**
     * csv文件对象
     */
    private final BufferedReader reader;

    /**
     * 字符集
     */
    private Charset charset;

    /**
     * CSVResource构造对象
     * 
     * @param inputStream csv文件流
     */
    public CSVReader(InputStream inputStream) {
        this(inputStream, StandardCharsets.UTF_8);
    }

    /**
     * CSVResource构造对象
     * 
     * @param inputStream csv文件流
     * @param charset     编码字符集
     */
    public CSVReader(InputStream inputStream, Charset charset) {
        this.charset = charset;
        this.reader = new BufferedReader(new InputStreamReader(inputStream, charset));
    }

    /**
     * 当前CSV的编码字符集
     * 
     * @return 编码字符集
     */
    public Charset getCharset() {
        return this.charset;
    }

    /**
     * 获取CSV文件中的所有行
     * 
     * @return 字符串行
     */
    public List<String> readAllLines() {
        return this.reader.lines().toList();
    }

    /**
     * 将CSV文件中的字符串转换为对象
     * 
     * @param <T>        转换的对象泛型
     * @param function   对象转换器
     * @param skipHeader 是否跳过第一行
     * @return 列表
     */
    public <T> List<T> cover(Function<String[], T> function, boolean skipHeader) {
        var stream = skipHeader ? this.reader.lines().skip(1) : this.reader.lines();
        return stream
                .filter(Objects::nonNull)
                .map(COLMUN_COVER)
                .map(function).toList();
    }

    /**
     * 关闭CSVResource文件流
     */
    public void close() {
        IOHepler.close(reader);
    }

    /**
     * CSV column字符反序列化
     */
    public static class CSVStringColmunCover implements Function<String, String[]> {

        /**
         * 将CSV行解析为字符串数组，如果是字符串类型则删除两侧的标点符号
         * todo 这里存在一个bug
         */
        @Override
        public String[] apply(String t) {
            if (t == null) {
                return new String[0];
            }
            String[] result = t.split(",");
            for (int index = 0; index < result.length; index++) {
                String item = result[index];
                if (item.startsWith("\"")) {
                    result[index] = StringHepler.trimBothEndsChars(item, "\"");
                }
            }
            return result;
        }

    }

}
