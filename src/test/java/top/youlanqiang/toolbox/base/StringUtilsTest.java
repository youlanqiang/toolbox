package top.youlanqiang.toolbox.base;

import static org.junit.jupiter.api.Assertions.*;
import static top.youlanqiang.toolbox.base.StringUtils.*;

import org.junit.jupiter.api.Test;

/**
 * StringUtils相关方法测试类
 * 
 * @see top.youlanqiang.toolbox.base.StringUtils
 */
public class StringUtilsTest {

    @Test
    void testHasLength() {
        assertTrue(hasLength("hello,toolbox"));
        assertFalse(hasLength(""));
        assertFalse(hasLength(null));
        assertFalse(hasLength(new StringBuilder()));

    }

    @Test
    void testHasText() {
        assertTrue(hasText("hello,toolbox"));
        assertFalse(hasText("    "));
        assertFalse(hasText(""));
        assertFalse(hasText(null));
        assertFalse(hasLength(new StringBuilder()));
    }

    @Test()
    void testIsEmpty() {
        assertTrue(isEmpty(null));
        assertFalse(isEmpty(""));
    }

    @Test()
    void testIsDigit() {
        assertTrue(isDigit('9'));
        assertTrue(isDigit('0'));
        assertFalse(isDigit('s'));
    }

    @Test()
    void testIsNumber() {
        assertTrue(isNumber("100"));
        assertTrue(isNumber("100.14"));
        assertTrue(isNumber("-100"));
        assertTrue(isNumber("-100.42"));
        assertFalse(isNumber(""));
        assertFalse(isNumber("dawwww"));
        assertFalse(isNumber(null));
    }

    @Test()
    void testFormat() {
        assertEquals("hello,world", format("hello,{}", "world"));
    }

}
