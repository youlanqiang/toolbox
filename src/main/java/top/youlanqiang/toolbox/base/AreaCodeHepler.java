package top.youlanqiang.toolbox.base;

/**
 * 区域代码帮助类
 * 
 * @author youlanqiang
 */
public class AreaCodeHepler {

    /**
     * 中国省市区代码
     */
    public record ChinaAreaCode(Integer id, Integer pid,
            Integer deep, String name, String pinyinPrefix, String pinyin,
            String extId, String extName) {
    }

}
