package top.youlanqiang.toolbox.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

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

    public PropertiesResource(String path) throws IOException {
        this.properties = new Properties();
        this.mode = PropertiesMode.FILE;
        properties.load(Files.newInputStream(Paths.get(path), StandardOpenOption.READ));
    }

    public PropertiesResource(final Optional<Map<String, Object>> mapOptional) {
        this.map = mapOptional.get();
        this.mode = PropertiesMode.MAP;
    }

    public Integer getInt(String key) {
        return getInt(key, null);
    }

    public Integer getInt(String key, Integer defaultVal) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }

        if (defaultVal != null && value == null) {
            return defaultVal;
        }

        return ObjectHepler.ObjectCastHepler.INSTANCE.castToInteger(value);
    }

    public Long getLong(String key) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }
        return ObjectHepler.ObjectCastHepler.INSTANCE.castToLong(value);
    }

    public String getString(String key) {
        Object value = null;
        if (mode == PropertiesMode.MAP) {
            value = map.get(key);
        } else {
            value = properties.getProperty(key);
        }
        return ObjectHepler.ObjectCastHepler.INSTANCE.castToString(value);
    }

    public PropertiesMode getMode() {
        return this.mode;
    }

    public enum PropertiesMode {
        MAP, FILE;
    }

}
