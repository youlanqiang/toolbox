package top.youlanqiang.toolbox.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapCache<K, V> implements Cache<K, V> {

    private final ConcurrentMap<K, V> map = new ConcurrentHashMap<K, V>();

    @Override
    public void put(K key, V value) {
        map.put(key, value);
    }

    @Override
    public V getIfPresent(K key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIfPresent'");
    }

    @Override
    public long size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public void cleanup() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cleanup'");
    }

}
