package top.youlanqiang.toolbox.csv;

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
import top.youlanqiang.toolbox.base.StringHepler;

/**
 * @author youlanqiang
 */
public class CSVResource implements Closeable {

    private static final CSVStringColmunCover COLMUN_COVER = new CSVStringColmunCover();

    /**
     * csv文件对象
     */
    private final BufferedReader reader;

    /**
     * 字符集
     */
    private Charset charset;

    public CSVResource(InputStream inputStream) {
        this(inputStream, StandardCharsets.UTF_8);
    }

    public CSVResource(InputStream inputStream, Charset charset) {
        this.charset = charset;
        this.reader = new BufferedReader(new InputStreamReader(inputStream, charset));
    }

    public Charset getCharset() {
        return this.charset;
    }

    public List<String> readAllLines() {
        return this.reader.lines().toList();
    }

    public <T> List<T> cover(Function<String[], T> function, boolean skipHeader) {
        var stream = skipHeader ? this.reader.lines().skip(1) : this.reader.lines();
        return stream
                .filter(Objects::nonNull)
                .map(COLMUN_COVER)
                .map(function).toList();
    }

    public void close() {
        IOHepler.close(reader);
    }

    public static class CSVStringColmunCover implements Function<String, String[]> {

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
