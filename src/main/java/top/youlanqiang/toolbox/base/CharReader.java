package top.youlanqiang.toolbox.base;

import java.io.IOException;
import java.io.Reader;

/**
 * 字符Reader类
 * 
 * @author youlanqiang
 */
public class CharReader {

    private Reader reader;

    private char[] buffer;

    private int pos = 0;

    private int size = 0;

    public CharReader(Reader reader) {
        this.reader = reader;
        this.buffer = new char[PACKAGE_CONST.DEFAULT_BUFFER_SIZE];
    }

    /**
     * 返回pos下标处的字符
     * 
     * @return char
     */
    public char peek() {
        if (pos - 1 >= size) {
            return (char) -1;
        }
        // 确保不会出现负数下标
        return buffer[Math.max(0, pos - 1)];
    }

    /**
     * 返回pos下标处的字符，并将pos+1
     * 
     * @return
     * @throws IOException
     */
    public char next() throws IOException {
        if (!hasMore()) {
            return (char) -1;
        }
        return buffer[pos++];
    }

    /**
     * 判断buffer是否还有未读的字符，如果没有则先{@link #fillBuffer}
     * 继续判断是否存在未读的字符
     * 
     * @return
     * @throws IOException
     */
    public boolean hasMore() throws IOException {
        if (pos < size) {
            return true;
        }

        fillBuffer();
        return pos < size;

    }

    /**
     * pos减一
     * todo 下标为0时back无效，后续可以考虑新增回到上步操作
     */
    public void back() {
        // 确保不会出现负数下标
        this.pos = Math.max(0, --pos);
    }

    /**
     * 填充buffer,并重置pos和size
     * 
     * @throws IOException
     */
    void fillBuffer() throws IOException {
        int n = reader.read(buffer);

        if (n == -1) {
            return;
        }

        pos = 0;
        size = n;
    }

    @Override
    public String toString() {
        return StringHepler
                .build(getClass())
                .put("buffer", buffer)
                .toString();
    }

}
