package top.youlanqiang.toolbox.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.io.IOHepler;

@DisplayName("IOHepler测试类")
public class IOHeplerTest {

    @DisplayName("测试读取resources下文件字符串是否正确")
    @Test
    public void testGetClassPathStream() throws IOException {
        assertEquals("This is a testcase.", IOHepler.getResourceAsStr("test.txt"));
    }

}
