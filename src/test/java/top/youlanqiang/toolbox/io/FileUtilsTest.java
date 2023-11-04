package top.youlanqiang.toolbox.io;

import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.math.BigIntegerUtils;

import static org.junit.jupiter.api.Assertions.*;
import static top.youlanqiang.toolbox.io.FileUtils.*;

import java.math.BigInteger;
import java.nio.file.Files;

public class FileUtilsTest {

    @Test
    void testByteCountToDisplaySize() {
        assertEquals("1MB", byteCountToDisplaySize(ONE_MB_BI));
        assertEquals("1MB1KB1bytes", byteCountToDisplaySize(BigIntegerUtils.sum(ONE_MB_BI,
                ONE_KB_BI, BigInteger.ONE)));
    }

    @Test
    void testGetFileExtension() {
        assertEquals("txt", getFileExtension("hello.txt"));
        assertEquals("txt", getFileExtension("/root/hello.txt"));
    }

    @Test
    void testGetNameWithoutExtension() {
        assertEquals("hello", getNameWithoutExtension("hello.txt"));
        assertEquals("hello", getNameWithoutExtension("/root/hello.txt"));
    }
}
