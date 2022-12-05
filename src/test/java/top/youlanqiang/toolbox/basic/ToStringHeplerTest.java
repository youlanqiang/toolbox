package top.youlanqiang.toolbox.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.Toolbox;

/**
 * @author youlanqiang
 *         created in 2022/12/05 20:00
 */
@DisplayName("ToStringHepler测试类")
public class ToStringHeplerTest {

    @DisplayName("测试format方法")
    @Test
    public void testFormat() {
        var str = Toolbox.format("This is {}", "one");
        assertEquals("This is one", str);
    }

    @DisplayName("测试多个参数format方法")
    @Test
    public void testMultiFormat() {
        var str = Toolbox.format("This is {} {} {}", "one", 1, false);
        assertEquals("This is one 1 false", str);
    }

    @DisplayName("测试转移字符format")
    @Test
    public void testDelim() {
        var str = Toolbox.format("This is \\{} {}", "one");
        assertEquals("This is {} one", str);
    }

}
