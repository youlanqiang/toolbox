package top.youlanqiang.toolbox.base;

import java.util.HashMap;
import java.util.Map;

import top.youlanqiang.toolbox.csv.CSVResource;

/**
 * 中国区域代码辅助类
 * 
 * @author youlanqiang
 */
public class ChinaAreaHepler {

    private static final Map<Integer, ChinaAreaCode> areaCodeMap = new HashMap<>();

    static {
        // 读取china_area_code.csv文件
        try (var csvResource = new CSVResource(IOHepler.getResourceAsStream("china_area_code.csv"))) {
            var areaCodes = csvResource.cover((array) -> {
                return new ChinaAreaCode(
                        Integer.parseInt(array[0]),
                        Integer.parseInt(array[1]),
                        Integer.parseInt(array[2]),
                        array[3],
                        array[4],
                        array[5],
                        array[6],
                        array[7]);
            }, true);
            areaCodes.forEach(areaCode -> {
                areaCodeMap.put(areaCode.id, areaCode);
            });
        }
    }

    /**
     * 中国省市区代码
     */
    public static record ChinaAreaCode(Integer id, Integer pid,
            Integer deep, String name, String pinyinPrefix, String pinyin,
            String extId, String extName) {
    }

    /**
     * 根据区域代码获取区域信息
     * 
     * @param code 区域代码 如:330000
     * @return 区域详细信息，包含区域名称等信息
     */
    public static ChinaAreaCode getAreaByCode(String code) {
        int id = Integer.parseInt(StringHepler.trimBothEndsChars(code, "0"));
        return areaCodeMap.get(id);
    }

}
