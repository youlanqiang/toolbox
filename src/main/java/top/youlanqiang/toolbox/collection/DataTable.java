package top.youlanqiang.toolbox.collection;

import java.util.Map;
import java.util.Set;

/**
 * DataTable数据结构，类似于Excel的二维数据表
 * @param <R> 行
 * @param <C> 列
 * @param <V> 值
 */
public interface DataTable<R, C, V> {

    /**
     * 判断是否存在
     * @param rowKey 行号
     * @param columnKey 列号
     * @return true为存在，false为不存在
     */
    boolean contains(R rowKey, C columnKey);


    /**
     * 判断行是否存在
     * @param rowKey 行号
     * @return true为存在，false为不存在
     */
    boolean containsRow(R rowKey);

    /**
     * 判断列是否存在
     * @param columnKey 列号
     * @return true为存在，false为不存在
     */
    boolean containsColumn(C columnKey);

    /**
     * 获取值
     * @param rowKey 行号
     * @param columnKey 列号
     * @return 获取对应的值
     */
    V get(R rowKey, C columnKey);

    /**
     * 获取行的所有列值
     * @param rowKey 行号
     * @return 对应行所拥有的列值
     */
    Map<C, V> getRows(R rowKey);

    /**
     * 获取列的所有行值
     * @param columnKey 列号
     * @return 对应列所拥有的列值
     */
    Map<R, V> getColumns(C columnKey);

    /**
     * 返回 key为rowKey的map集合
     * @return rowMap
     */
    Map<R, Map<C, V>> rowMap();

    /**
     * 返回 key为columnKey的map集合
     * @return columnMap
     */
    Map<C, Map<R, V>> columnMap();

    /**
     * 插入数据
     * @param rowKey 行号
     * @param columnKey 列号
     * @param value 值
     * @return 值
     */
    V put(R rowKey, C columnKey, V value);

    /**
     * 获取所有行号
     * @return 所有行号
     */
    Set<R> rowKeys();

    /**
     * 获取所有列号
     * @return 所有列号
     */
    Set<C> columnKeys();

    /**
     * 返回当前table是否为空
     * @return true表示该table为空
     */
    boolean isEmpty();

    /**
     * 返回value值数量
     * @return value值数量
     */
    int size();

}
