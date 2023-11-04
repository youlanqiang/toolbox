package top.youlanqiang.toolbox.math;

import java.math.BigInteger;

/**
 * BigInteger类相关工具方法
 */
public final class BigIntegerUtils {

    /**
     * 将传入的BigInteger数组求和，会忽略null对象
     * 
     * @param values BigInteger数组
     * @return 求和
     */
    public static BigInteger sum(BigInteger... values) {
        BigInteger result = BigInteger.ZERO;
        for (BigInteger value : values) {
            if (value != null) {
                result = result.add(value);
            }
        }

        return result;
    }

    private BigIntegerUtils() {
    }
}
