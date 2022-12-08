package top.youlanqiang.toolbox.collection;

import top.youlanqiang.toolbox.Toolbox;

/**
 * @author youlanqiang
 *         created in 2022/10/14 22:34
 *         Triple提供返回3个元素组成的对象
 */
public abstract class Triple<L, M, R> implements Comparable<Triple<L, M, R>> {

    private Triple() {
    };

    public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right) {
        return new ImmutableTriple<>(left, middle, right);
    }

    public abstract L getLeft();

    public abstract M getMiddle();

    public abstract R getRight();

    public abstract Triple<L, M, R> setLeft(L left);

    public abstract Triple<L, M, R> setMiddle(M middle);

    public abstract Triple<L, M, R> setRight(R right);

    @Override
    public String toString() {
        return Toolbox.toString(this)
                .put("left", getLeft())
                .put("middle", getMiddle())
                .put("right", getRight())
                .toString();
    }

    /**
     * 不可变Triple对象
     * 
     * @param <L> 左值
     * @param <M> 中值
     * @param <R> 右值
     */
    private static class ImmutableTriple<L, M, R> extends Triple<L, M, R> {

        private final L left;

        private final M middle;

        private final R right;

        public ImmutableTriple(L left, M middle, R right) {
            this.left = left;
            this.middle = middle;
            this.right = right;
        }

        @Override
        public L getLeft() {
            return this.left;
        }

        @Override
        public M getMiddle() {
            return this.middle;
        }

        @Override
        public R getRight() {
            return this.right;
        }

        @Override
        public Triple<L, M, R> setLeft(L left) {
            throw new IllegalArgumentException(PACKAGE_CONST.IMMUTABLE_ERROR);
        }

        @Override
        public Triple<L, M, R> setMiddle(M middle) {
            throw new IllegalArgumentException(PACKAGE_CONST.IMMUTABLE_ERROR);
        }

        @Override
        public Triple<L, M, R> setRight(R right) {
            throw new IllegalArgumentException(PACKAGE_CONST.IMMUTABLE_ERROR);
        }

        @Override
        public int compareTo(Triple<L, M, R> o) {
            return 0;
        }
    }
}
