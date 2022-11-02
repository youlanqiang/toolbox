package top.youlanqiang.toolbox.collection;

import java.util.List;

/**
 * @author youlanqiang
 * created in 2022/10/15 22:33
 * 树级对象
 */
public class TreeNode<K, V> {


    private K key;

    private V value;

    private List<TreeNode<K, V>> children;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public List<TreeNode<K, V>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<K, V>> children) {
        this.children = children;
    }


}
