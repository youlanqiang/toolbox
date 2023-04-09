package top.youlanqiang.toolbox.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.base.ChinaAreaHepler;

@DisplayName("ChinaAreaHepler测试类")
public class ChinaAreaHeplerTest {

    @Test
    @DisplayName("测试获取ChinaArea")
    public void test() {
        var area = ChinaAreaHepler.getAreaByCode("330000");
        assertEquals("浙江", area.name());
    }

}
