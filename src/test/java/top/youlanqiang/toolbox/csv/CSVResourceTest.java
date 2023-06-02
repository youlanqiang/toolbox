package top.youlanqiang.toolbox.csv;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.base.IOHepler;
import top.youlanqiang.toolbox.base.ChinaAreaHepler.ChinaAreaCode;
import top.youlanqiang.toolbox.reader.CSVReader;

@DisplayName("CSVResourceTest测试类")
public class CSVResourceTest {

    private static final String FILE_PATH = "china_area_code.csv";

    @Test
    @DisplayName("测试CSVResource读取csv文件")
    public void testLoad() {
        CSVReader resource = new CSVReader(IOHepler.getResourceAsStream(FILE_PATH));
        assertTrue(resource.readAllLines().size() != 0);
    }

    @Test
    @DisplayName("测试CSVResource读取csv文件,并转换为实体类")
    public void testConver() {
        CSVReader resource = new CSVReader(IOHepler.getResourceAsStream(FILE_PATH));
        var areaCodes = resource.cover((array) -> {
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
        assertTrue(areaCodes.size() != 0);
    }

}
