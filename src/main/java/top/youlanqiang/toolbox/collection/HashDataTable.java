package top.youlanqiang.toolbox.collection;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class HashDataTable<R,C,V> implements DataTable<R,C,V>, Serializable {

    /**
     * 直接使用2个map来保存row和column，内存占用2倍，但是查询与写入操作都是O(1)
     */
    final Map<R, Map<C, V>> backingRowMap;

    final Map<C, Map<R, V>> backingColumnMap;

    public HashDataTable(){
        backingRowMap = new ConcurrentHashMap<>();
        backingColumnMap = new ConcurrentHashMap<>();
    }

    @Override
    public boolean contains(R rowKey, C columnKey) {
        if(backingRowMap.containsKey(rowKey)){
            Map<C, V> rowMap = backingRowMap.get(rowKey);
            if(rowMap != null){
                return rowMap.containsKey(columnKey);
            }
        }
        return false;
    }

    @Override
    public boolean containsRow(R rowKey) {
        return backingRowMap.containsKey(rowKey);
    }

    @Override
    public boolean containsColumn(C columnKey) {
        return backingColumnMap.containsKey(columnKey);
    }

    @Override
    public V get(R rowKey, C columnKey) {
        if(backingRowMap.containsKey(rowKey)){
            Map<C, V> rowMap = backingRowMap.get(rowKey);
            if(rowMap != null){
                return rowMap.get(columnKey);
            }
        }
        return null;
    }

    @Override
    public Map<C, V> getRows(R rowKey) {
        return backingRowMap.get(rowKey);
    }

    @Override
    public Map<R, V> getColumns(C columnKey) {
        return backingColumnMap.get(columnKey);
    }

    @Override
    public Map<R, Map<C, V>> rowMap() {
        return backingRowMap;
    }

    @Override
    public Map<C, Map<R, V>> columnMap() {
        return backingColumnMap;
    }

    @Override
    public V put(R rowKey, C columnKey, V value) {
        backingRowMap.computeIfAbsent(rowKey, (key)-> new ConcurrentHashMap<>()).put(columnKey, value);
        backingColumnMap.computeIfAbsent(columnKey, (key)-> new ConcurrentHashMap<>()).put(rowKey, value);
        return value;
    }


    @Override
    public Set<R> rowKeys() {
        return backingRowMap.keySet();
    }

    @Override
    public Set<C> columnKeys() {
        return backingColumnMap.keySet();
    }

    @Override
    public boolean isEmpty() {
        return backingRowMap.isEmpty();
    }

    @Override
    public int size() {
        int size = 0;
        for (Map<C, V> value : backingRowMap.values()) {
            size += value.size();
        }
        return size;
    }


}
