package top.youlanqiang.toolbox.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * 百分比的工具类
 * 百分比的计算公式如下：百分比 = (部分 / 总数) * 100
 */
public final class PercentageUtils {

    /**
     * 计算2数的百分比占比，无法计算负数，且如果传值为null或小于0则直接返回0
     * 
     * @param part         分子
     * @param total        分母
     * @param fraction     保留小数几位
     * @param roundingMode 四舍五入模式
     * @return double类型，分子占分母的百分比
     */
    public static double getPercentage(Number part, Number total, int fraction, RoundingMode roundingMode) {
        if (part == null || total == null || total.doubleValue() <= 0.0 || part.doubleValue() <= 0.0) {
            return 0;
        }
        BigDecimal partDec = BigDecimal.valueOf(part.doubleValue());
        BigDecimal totalDec = BigDecimal.valueOf(total.doubleValue());
        BigDecimal divide = partDec.divide(totalDec).setScale(fraction, roundingMode);
        return divide.multiply(BigDecimal.valueOf(100L)).doubleValue();
    }

    /**
     * 计算2数的百分比占比，无法计算负数，且如果传值为null或小于0则直接返回0
     * 
     * @param part         分子
     * @param total        分母
     * @param fraction     保留小数几位
     * @param roundingMode 四舍五入模式
     * @return 数字后面带%字符的字符串
     */
    public static String getPercentageStr(Number part, Number total, int fraction, RoundingMode roundingMode) {
        double percentage = getPercentage(part, total, fraction, roundingMode);
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(fraction);
        return numberFormat.format(percentage);
    }

    private PercentageUtils() {
    }
}
