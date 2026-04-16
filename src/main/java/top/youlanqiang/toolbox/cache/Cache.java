package top.youlanqiang.toolbox.cache;

public interface Cache<K, V> {

    V getIfPresent(K key);

    void put(K key, V value);

    long size();

    void cleanup();

}
