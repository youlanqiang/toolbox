package top.youlanqiang.toolbox.collection;

import java.util.Map.Entry;

import top.youlanqiang.toolbox.Toolbox;

/**
 * @author youlanqiang
 *         created in 2022/10/14 22:18
 *         Pair提供返回2个元素组成的对象
 */
public abstract class Pair<L, R> implements Entry<L, R> {

    private Pair() {
    };

    /**
     * 获取左值
     * 
     * @return 左值
     */
    public abstract L getLeft();

    /**
     * 获取右值
     * 
     * @return 右值
     */
    public abstract R getRight();

    /**
     * 设置左值，对象是不可变对象时直接报错
     * 
     * @param left 左值
     * @return this
     */
    public abstract Pair<L, R> setLeft(L left);

    /**
     * 设置右值，对象是不可变对象时直接报错
     * 
     * @param right 右值
     * @return this
     */
    public abstract Pair<L, R> setRight(R right);

    @Override
    public L getKey() {
        return getLeft();
    }

    @Override
    public R getValue() {
        return getRight();
    }

    @Override
    public R setValue(R value) {
        R oldValue = getRight();
        this.setRight(value);
        return oldValue;
    }

    /**
     * 创建一个不可变的Pair对象
     * 
     * @see ImmutablePair
     * @param <L>   左值泛型
     * @param <R>   右值泛型
     * @param left  左值
     * @param right 右值
     * @return ImmutablePair
     */
    public static <L, R> Pair<L, R> of(L left, R right) {
        return new ImmutablePair<>(left, right);
    }

    /**
     * 创建一个可变的Pair空对象
     * 
     * @see MutablePair
     * @param <L> 左值泛型
     * @param <R> 右值泛型
     * @return MutablePair
     */
    public static <L, R> Pair<L, R> build() {
        return new MutablePair<>();
    }

    /**
     * 创建一个可变的Pair对象
     * 
     * @see MutablePair
     * @param <L>   左值泛型
     * @param <R>   右值泛型
     * @param left  左值
     * @param right 右值
     * @return MutablePair
     */
    public static <L, R> Pair<L, R> build(L left, R right) {
        return new MutablePair<>(left, right);
    }

    @Override
    public String toString() {
        return Toolbox.toString(this)
                .put("left", getLeft())
                .put("right", getRight())
                .toString();
    }

    /**
     * 不可变Pair值
     * 
     * @param <L> 左值
     * @param <R> 右值
     */
    private static class ImmutablePair<L, R> extends Pair<L, R> {

        private final L leftValue;

        private final R rightValue;

        public ImmutablePair(L left, R right) {
            this.leftValue = left;
            this.rightValue = right;
        }

        @Override
        public L getLeft() {
            return leftValue;
        }

        @Override
        public R getRight() {
            return rightValue;
        }

        @Override
        public Pair<L, R> setLeft(L left) {
            throw new IllegalArgumentException(PACKAGE_CONST.IMMUTABLE_ERROR);
        }

        @Override
        public Pair<L, R> setRight(R right) {
            throw new IllegalArgumentException(PACKAGE_CONST.IMMUTABLE_ERROR);
        }

    }

    /**
     * 可变Pair对象
     * 
     * @param <L> 左值
     * @param <R> 右值
     */
    private static class MutablePair<L, R> extends Pair<L, R> {

        private L leftValue;

        private R rightValue;

        public MutablePair() {
        }

        public MutablePair(L left, R right) {
            this.leftValue = left;
            this.rightValue = right;
        }

        @Override
        public L getLeft() {
            return leftValue;
        }

        @Override
        public R getRight() {
            return rightValue;
        }

        @Override
        public Pair<L, R> setLeft(L left) {
            this.leftValue = left;
            return this;
        }

        @Override
        public Pair<L, R> setRight(R right) {
            this.rightValue = right;
            return this;
        }

    }
}
