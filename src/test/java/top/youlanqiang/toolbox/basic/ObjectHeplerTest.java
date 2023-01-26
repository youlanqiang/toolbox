package top.youlanqiang.toolbox.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.Toolbox;
import top.youlanqiang.toolbox.base.ObjectHepler;

@DisplayName("ObjectHepler测试类")
public class ObjectHeplerTest {

    @DisplayName("测试类型转换")
    @Test
    public void testCast() {
        assertTrue(Toolbox.castHepler().castToBoolean(1));
        assertEquals(Character.valueOf('1'), Toolbox.castHepler().castToCharacter(49));
        assertEquals(Byte.valueOf("10"), Toolbox.castHepler().castToByte("10"));
    }

    @DisplayName("测试isDigit方法")
    @Test
    public void testIsDigit() {
        assertTrue(ObjectHepler.isDigit('1'));
    }

}
