package top.youlanqiang.toolbox.basic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.text.AbstractLambdaStringBuilder;
import top.youlanqiang.toolbox.text.LambdaStringBuffer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

@DisplayName("AbstractLambdaStringBuilder测试类")
public class LambdaStringTest {

    private static final AbstractLambdaStringBuilder sb = new LambdaStringBuffer();

    @DisplayName("Delete相关操作测试")
    @Nested
    class DeleteTest {

        @BeforeEach
        public void beforeEach() {
            sb.clean();
        }

        @DisplayName("测试删除最后一个字符操作")
        @Test
        public void testDeleteLastChar() {
            assertEquals("1", sb.append("1,").deleteLastChar().toString());
        }
    }

    @DisplayName("Append相关操作测试")
    @Nested
    class AppendTest {

        @BeforeEach
        public void beforeEach() {
            sb.clean();
        }

        @DisplayName("测试Repeat方法")
        @Test
        public void testRepeat() {
            assertEquals("hellohello", sb.append("hello").repeat(2).toString());
        }

        @DisplayName("测试Append方法")
        @Test
        public void testAppend() {
            assertEquals("hello", sb.append(true, "hello").toString());
            // 上一步已经添加字符串hello
            assertEquals("hello", sb.append(false, "hello").toString());
        }

        @DisplayName("测试AppendCollection方法")
        @Test
        public void testAppendCollection() {
            assertEquals("1234", sb.append(Arrays.asList(1, 2, 3, 4)).toString());
        }

        @DisplayName("测试AppendArray方法")
        @Test
        public void testAppendArray() {
            assertEquals("1234", sb.append(1, 2, 3, 4).toString());
        }

    }

}
