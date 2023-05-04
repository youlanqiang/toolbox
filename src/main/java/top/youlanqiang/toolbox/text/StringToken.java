package top.youlanqiang.toolbox.text;

import top.youlanqiang.toolbox.base.StringHepler;

/**
 * 字符串Token
 * 
 * @author youlanqiang
 */
public class StringToken {

    /**
     * token下标，从0开始
     */
    private Integer index;

    /**
     * tokenValue token字符串
     */
    private String tokenValue;

    /**
     * defaultValue 默认字符串
     */
    private String defaultValue;

    /**
     * raw 原始token字符串
     */
    private String raw;

    public StringToken(Integer index, String tokenValue, String raw) {
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return StringHepler.build(getClass())
                .put("index", index)
                .put("tokenValue", tokenValue)
                .put("defaultValue", defaultValue)
                .put("raw", raw)
                .toString();
    }

}
