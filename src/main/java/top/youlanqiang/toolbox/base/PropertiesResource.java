package top.youlanqiang.toolbox.base;

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
import java.util.stream.Stream;

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
    private Map<String, Object> map;

    /**
     * PropertiesResource是从file中获取值还是从map中获取值
     */
    private PropertiesMode mode = PropertiesMode.FILE;

    public static PropertiesResource loadFromInputStream(InputStream stream) throws IOException {
        return loadFromInputStream(stream, StandardCharsets.UTF_8);
    }

    public static PropertiesResource loadFromResource(String filePath) throws IOException {
        return loadFromResource(filePath, StandardCharsets.UTF_8);
    }

    public static PropertiesResource loadFromResource(String filePath, Charset charset) throws IOException {
        return loadFromInputStream(IOHepler.getResourceAsStream(filePath), charset);
    }

    public static PropertiesResource loadFromInputStream(InputStream stream, Charset charset) throws IOException {
        try {
            return new PropertiesResource(stream, charset);
        } finally {
            IOHepler.close(stream);
        }
    }

    public PropertiesResource(InputStream stream) throws IOException {
        this(stream, StandardCharsets.UTF_8);
    }

    public PropertiesResource(InputStream stream, Charset charset) throws IOException {
        this(new InputStreamReader(stream, charset));
    }

    public PropertiesResource(Reader reader) throws IOException {
        this.properties = new Properties();
        this.mode = PropertiesMode.FILE;
        properties.load(reader);
    }

    public PropertiesResource(final Optional<Map<String, Object>> mapOptional) {
        this.map = mapOptional.get();
        this.mode = PropertiesMode.MAP;
    }

    public Byte getByte(String key) {
        return getByte(key, null);
    }

    public Short getShort(String key) {
        return getShort(key, null);
    }

    public Integer getInt(String key) {
        return getInt(key, null);
    }

    public Long getLong(String key) {
        return getLong(key, null);
    }

    public Float getFloat(String key) {
        return getFloat(key, null);
    }

    public Double getDouble(String key) {
        return getDouble(key, null);
    }

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

    public String getString(String key) {
        return getString(key, null);
    }

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

    @Override
    public String toString() {
        if (mode == PropertiesMode.MAP) {
            return ToStringHepler.mapToString(map, ",", "=");
        } else {
            return ToStringHepler.mapToString(properties, ",", "=");
        }
    }

    public PropertiesMode getMode() {
        return this.mode;
    }

    public enum PropertiesMode {
        MAP, FILE;
    }

}
