package top.youlanqiang.toolbox.collection;

import top.youlanqiang.toolbox.Toolbox;

/**
 * 
 * Triple提供返回3个元素组成的对象
 * 
 * @author youlanqiang
 *         created in 2022/10/14 22:34
 * 
 */
public abstract class Triple<L, M, R> {

    private Triple() {
    };

    /**
     * 创建一个不可变Triple对象
     * 
     * @param <L>    左值泛型
     * @param <M>    中值泛型
     * @param <R>    右值泛型
     * @param left   左值
     * @param middle 中值
     * @param right  右值
     * @return 不可变Triple对象
     */
    public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right) {
        return new ImmutableTriple<>(left, middle, right);
    }

    /**
     * 创建一个可变Triple对象
     * 
     * @param <L> 左值泛型
     * @param <M> 中值泛型
     * @param <R> 右值泛型
     * @return 可变Triple对象
     */
    public static <L, M, R> Triple<L, M, R> build() {
        return new MutableTriple<>();
    }

    /**
     * 获取左值
     * 
     * @return 左值
     */
    public abstract L getLeft();

    /**
     * 获取中值
     * 
     * @return 中值
     */
    public abstract M getMiddle();

    /**
     * 获取右值
     * 
     * @return 右值
     */
    public abstract R getRight();

    /**
     * 设置左值
     * 
     * @param left 左值
     * @return this
     */
    public abstract Triple<L, M, R> setLeft(L left);

    /**
     * 设置中值
     * 
     * @param middle 中值
     * @return this
     */
    public abstract Triple<L, M, R> setMiddle(M middle);

    /**
     * 设置右值
     * 
     * @param right 右值
     * @return this
     */
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

    }

    /**
     * 可变Triple对象
     */
    private static class MutableTriple<L, M, R> extends Triple<L, M, R> {

        private L left;

        private M middle;

        private R right;

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
            this.left = left;
            return this;
        }

        @Override
        public Triple<L, M, R> setMiddle(M middle) {
            this.middle = middle;
            return this;
        }

        @Override
        public Triple<L, M, R> setRight(R right) {
            this.right = right;
            return this;
        }

    }
}
