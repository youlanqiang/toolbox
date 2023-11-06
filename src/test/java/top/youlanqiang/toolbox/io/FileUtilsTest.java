package top.youlanqiang.toolbox.io;

import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.math.BigIntegerUtils;

import static org.junit.jupiter.api.Assertions.*;
import static top.youlanqiang.toolbox.io.FileUtils.*;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.zip.CRC32;

public class FileUtilsTest {

    @Test
    void testEquals() throws IOException {
        File file1 = new File("/Users/youlanqiang/IdeaProjects/toolbox/src/test/resources/test.txt");
        File file2 = new File("/Users/youlanqiang/IdeaProjects/toolbox/src/test/resources/test2.txt");
        File file3 = new File("/Users/youlanqiang/IdeaProjects/toolbox/src/test/resources/test3.txt");

        assertTrue(contentEquals(file1, file1));
        assertTrue(contentEquals(file1, file2));
        assertFalse(contentEquals(file1, file3));

    }

    @Test
    void testChecksum() throws IOException {
        File file = new File("/Users/youlanqiang/IdeaProjects/toolbox/src/test/resources/test.txt");
        assertNotNull(checksum(file, new CRC32()).getValue());
    }

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
