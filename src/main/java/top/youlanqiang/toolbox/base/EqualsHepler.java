package top.youlanqiang.toolbox.base;

/**
 * 等值比较器
 * 用于比较属性是否相同,可以添加多个比较条件然后返回一个比较结果。
 * 
 * @author youlanqiang
 *         created in 2022/12/03 09:56
 * 
 */
public final class EqualsHepler {

    private boolean equalsValue = true;

    /**
     * 添加比较条件值
     * 
     * @param v1 值
     * @param v2 被比较值
     * @return this
     */
    public EqualsHepler addCondition(Object v1, Object v2) {
        if (!equalsValue) {
            return this;
        }
        this.equalsValue = v1.equals(v2);
        return this;
    }

    /**
     * 返回比较结果
     * 
     * @return true or false
     */
    public boolean doEquals() {
        return equalsValue;
    }

}
