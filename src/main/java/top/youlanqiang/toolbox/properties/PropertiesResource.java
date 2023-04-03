package top.youlanqiang.toolbox.properties;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import top.youlanqiang.toolbox.base.IOHepler;
import top.youlanqiang.toolbox.base.ObjectHepler;
import top.youlanqiang.toolbox.base.StringHepler;

/**
 * Properties文件读取类
 * 
 * @author youlanqiang
 *         created in 2022/12/24 16:34
 * 
 */
public class PropertiesResource {

    /**
     * properties对象
     */
    private Properties properties;

    /**
     * map对象
     */
    private Map<String, ?> map;

    /**
     * PropertiesResource是从file中获取值还是从map中获取值
     */
    private PropertiesMode mode = PropertiesMode.FILE;

    /**
     * properties读取字符集
     */
    private Charset charset;

    /**
     * 读取file对应的properties文件
     * 
     * @param file 文件对象
     * @return PropertiesResource对象,UTF8编码
     * @throws IOException 读取失败报IOException异常
     */
    public static PropertiesResource loadFromFile(File file) throws IOException {
        return loadFromPath(file.toPath());
    }

    /**
     * 读取path对应的properties文件
     * 
     * @param path 文件对象
     * @return PropertiesResource对象,UTF8编码
     * @throws IOException 读取失败报IOException异常
     */
    public static PropertiesResource loadFromPath(Path path) throws IOException {
        return loadFromInputStream(Files.newInputStream(path, StandardOpenOption.READ));
    }

    /**
     * 读取stream流的properties文件
     * 
     * @param stream stream流
     * @return PropertiesResource对象,UTF8编码
     * @throws IOException 读取失败报IOException异常
     */
    public static PropertiesResource loadFromInputStream(InputStream stream) throws IOException {
        return loadFromInputStream(stream, StandardCharsets.UTF_8);
    }

    /**
     * 读取resources下的properties文件
     * 
     * @param filePath 相对文件路径
     * @return PropertiesResource对象,UTF8编码
     * @throws IOException 读取失败报IOException异常
     */
    public static PropertiesResource loadFromResource(String filePath) throws IOException {
        return loadFromResource(filePath, StandardCharsets.UTF_8);
    }

    /**
     * 读取resources下的properties文件
     * 
     * @param charset  编码字符集
     * @param filePath 相对文件路径
     * @return PropertiesResource对象,UTF8编码
     * @throws IOException 读取失败报IOException异常
     */
    public static PropertiesResource loadFromResource(String filePath, Charset charset) throws IOException {
        return loadFromInputStream(IOHepler.getResourceAsStream(filePath), charset);
    }

    /**
     * 读取stream流的properties文件
     * 
     * @param charset 编码字符集
     * @param stream  stream流
     * @return PropertiesResource对象,UTF8编码
     * @throws IOException 读取失败报IOException异常
     */
    public static PropertiesResource loadFromInputStream(InputStream stream, Charset charset) throws IOException {
        try {
            return new PropertiesResource(stream, charset);
        } finally {
            IOHepler.close(stream);
        }
    }

    /**
     * 读取stream流的properties文件,编码字符集默认为utf8
     * 
     * @param stream stream流
     * @throws IOException 读取失败报IOException异常
     */
    public PropertiesResource(InputStream stream) throws IOException {
        this(stream, StandardCharsets.UTF_8);
    }

    /**
     * 读取stream流的properties文件
     * 
     * @param stream  stream流
     * @param charset 编码字符集
     * @throws IOException 读取失败报IOException异常
     */
    public PropertiesResource(InputStream stream, Charset charset) throws IOException {
        this(new InputStreamReader(stream, charset));
        this.charset = charset;
    }

    /**
     * 读取reader的properties文件
     * 
     * @param reader reader对象
     * @throws IOException 读取失败报IOException异常
     */
    public PropertiesResource(Reader reader) throws IOException {
        this.properties = new Properties();
        this.mode = PropertiesMode.FILE;
        properties.load(reader);
    }

    /**
     * map模式的resource对象
     * 
     * @param map map对象不可为空
     */
    public PropertiesResource(final Optional<Map<String, ?>> map) {
        this.map = map.get();
        this.mode = PropertiesMode.MAP;
    }

    /**
     * 获取byte值
     * 
     * @param key properties中的key
     * @return value值,获取不到返回null
     */
    public Byte getByte(String key) {
        return getByte(key, null);
    }

    /**
     * 获取short值
     * 
     * @param key properties中的key
     * @return value值,获取不到返回null
     */
    public Short getShort(String key) {
        return getShort(key, null);
    }

    /**
     * 获取int值
     * 
     * @param key properties中的key
     * @return value值,获取不到返回null
     */
    public Integer getInt(String key) {
        return getInt(key, null);
    }

    /**
     * 获取long值
     * 
     * @param key properties中的key
     * @return value值,获取不到返回null
     */
    public Long getLong(String key) {
        return getLong(key, null);
    }

    /**
     * 获取float值
     * 
     * @param key properties中的key
     * @return value值,获取不到返回null
     */
    public Float getFloat(String key) {
        return getFloat(key, null);
    }

    /**
     * 获取double值
     * 
     * @param key properties中的key
     * @return value值,获取不到返回null
     */
    public Double getDouble(String key) {
        return getDouble(key, null);
    }

    /**
     * 获取byte值
     * 
     * @param key        properties中的key
     * @param defaultVal 获取不到返回的默认值
     * @return value值
     */
    public Byte getByte(String key, Byte defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToByte(value);
    }

    /**
     * 获取short值
     * 
     * @param key        properties中的key
     * @param defaultVal 获取不到返回的默认值
     * @return value值
     */
    public Short getShort(String key, Short defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToShort(value);
    }

    /**
     * 获取int值
     * 
     * @param key        properties中的key
     * @param defaultVal 获取不到返回的默认值
     * @return value值
     */
    public Integer getInt(String key, Integer defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToInteger(value);
    }

    /**
     * 获取long值
     * 
     * @param key        properties中的key
     * @param defaultVal 获取不到返回的默认值
     * @return value值
     */
    public Long getLong(String key, Long defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToLong(value);
    }

    /**
     * 获取float值
     * 
     * @param key        properties中的key
     * @param defaultVal 获取不到返回的默认值
     * @return value值
     */
    public Float getFloat(String key, Float defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToFloat(value);
    }

    /**
     * 获取double值
     * 
     * @param key        properties中的key
     * @param defaultVal 获取不到返回的默认值
     * @return value值
     */
    public Double getDouble(String key, Double defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToDouble(value);
    }

    /**
     * 获取boolean值
     * 
     * @param key        properties中的key
     * @param defaultVal 获取不到返回的默认值
     * @return value值
     */
    public Boolean getBoolean(String key, Boolean defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToBoolean(value);
    }

    /**
     * 获取String值
     * 
     * @param key properties中的key
     * @return value值,获取不到返回null
     */
    public String getString(String key) {
        return getString(key, null);
    }

    /**
     * 获取String值
     * 
     * @param key        properties中的key
     * @param defaultVal 获取不到返回的默认值
     * @return value值
     */
    public String getString(String key, String defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToString(value);
    }

    /**
     * 获取编码字符集
     * 
     * @return 字符集
     */
    public Charset getCharset() {
        return this.charset;
    }

    /**
     * 保存至文件
     * 
     * @param filePath path对象
     * @throws IOException 保存异常报的异常
     */
    public void saveToPath(Path filePath) throws IOException {
        saveToPath(filePath, null);
    }

    /**
     * 保存至文件
     * 
     * @param filePath path对象
     * @param comments 备注,可以为null
     * @throws IOException 保存异常报的异常
     */
    public void saveToPath(Path filePath, String comments) throws IOException {
        if (mode == PropertiesMode.MAP) {
            if (properties == null) {
                properties = new Properties();
            }
            properties.putAll(map);
        }
        properties.store(Files.newBufferedWriter(filePath, this.charset, StandardOpenOption.WRITE), comments);
    }

    @Override
    public String toString() {
        if (mode == PropertiesMode.MAP) {
            return StringHepler.mapToString(map, ",", "=");
        } else {
            return StringHepler.mapToString(properties, ",", "=");
        }
    }

    /**
     * 获取读取模式
     * 
     * @return PropertiesMod
     */
    public PropertiesMode getMode() {
        return this.mode;
    }

    /**
     * properties读取模式
     */
    public enum PropertiesMode {
        /**
         * 从map中读取
         */
        MAP,
        /**
         * 从文件中读取
         */
        FILE;
    }

}
