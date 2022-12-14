package top.youlanqiang.toolbox.basic;

public class LambdaStringBuffer {

    private final StringBuffer buffer;

    public LambdaStringBuffer() {
        this.buffer = new StringBuffer();
    }

    public StringBuffer append(Object obj) {
        return buffer.append(obj);
    }

    public StringBuffer append(boolean condition, Object obj) {
        if (condition) {
            return buffer.append(obj);
        }
        return buffer;
    }

    public int length() {
        return buffer.length();
    }

    public int capacity() {
        return buffer.capacity();
    }

    public int compareTo(StringBuffer arg) {
        return buffer.compareTo(arg);
    }

}
