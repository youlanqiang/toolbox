package top.youlanqiang.toolbox.basic;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.reader.PropertiesReader;

@DisplayName("PropertiesResource测试类")
public class PropertiesResourceTest {

    @DisplayName("测试读取Properties文件")
    @Test
    public void testLoadFile() throws IOException {
        PropertiesReader resource = PropertiesReader.loadFromResource("test.properties");
        System.out.println(resource.getString("test"));
    }

}
