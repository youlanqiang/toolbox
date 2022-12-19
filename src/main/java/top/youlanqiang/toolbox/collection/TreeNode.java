package top.youlanqiang.toolbox.collection;

import java.util.List;

/**
 * @author youlanqiang
 *         created in 2022/10/15 22:33
 *         层次树对象
 */
public class TreeNode<K, V> {

    private K key;

    private V value;

    private List<TreeNode<K, V>> children;

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
    public V getValue() {
        return value;
    }

    /**
     * 设置对象的value值
     * 
     * @param value 对象的value值
     */
    public void setValue(V value) {
        this.value = value;
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

}
