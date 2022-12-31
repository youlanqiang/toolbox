package top.youlanqiang.toolbox.collection;

import java.util.List;

/**
 * 层次树对象
 * 
 * @author youlanqiang
 *         created in 2022/10/15 22:33
 * 
 */
public class TreeNode<K, V> implements Comparable<TreeNode<K, V>> {

    private K key;

    private V raw;

    private List<TreeNode<K, V>> children;

    private Long soft;

    /**
     * 获取对象的key值，对象的唯一主键
     * 
     * @return key
     */
    public K getKey() {
        return key;
    }

    /**
     * 设置对象的key值
     * 
     * @param key key值
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * 获取对象的value值
     * 
     * @return 对象的value
     */
    public V getRaw() {
        return raw;
    }

    /**
     * 设置对象的value值
     * 
     * @param raw 对象的value值
     */
    public void setRaw(V raw) {
        this.raw = raw;
    }

    /**
     * 获取对象的子集
     * 
     * @return 对象的chilren集合
     */
    public List<TreeNode<K, V>> getChildren() {
        return children;
    }

    /**
     * 设置对象的子集
     * 
     * @param children children集合
     */
    public void setChildren(List<TreeNode<K, V>> children) {
        this.children = children;
    }

    /**
     * 排序
     * 
     * @return 对象的排序因子
     */
    public Long getSoft() {
        return this.soft;
    }

    /**
     * 设置对象的排序因子
     * 
     * @param soft 排序因子
     */
    public void setSoft(Long soft) {
        this.soft = soft;
    }

    /**
     * 比较2个TreeNode大小
     * 
     * @param o TreeNode
     * @return 0,1,-1
     */
    @Override
    public int compareTo(TreeNode<K, V> o) {
        return Long.compare(this.getSoft(), o.getSoft());
    }

}
