package top.youlanqiang.toolbox.collection;

/**
 * @author youlanqiang
 * created in 2022/10/14 22:34
 * Triple提供返回3个元素组成的对象
 */
public abstract class Triple<L, M, R> implements Comparable<Triple<L, M, R>> {

    private Triple(){};


    public abstract L getLeft();

    public abstract M getMiddle();

    public abstract R getRight();


    public abstract Triple<L, M, R> setLeft(L left);

    public abstract Triple<L, M, R> setMiddle(M middle);

    public abstract Triple<L, M, R> setRight(R right);

    @Override
    public String toString(){
        return "("+getLeft()+","+getMiddle()+","+getRight()+")";
    }

    /**
     * 不可变Triple对象
     * @param <L> 左值
     * @param <M> 中值
     * @param <R> 右值
     */
    private static class ImmutableTriple<L, M, R> extends Triple<L, M, R>{

        @Override
        public L getLeft() {
            return null;
        }

        @Override
        public M getMiddle() {
            return null;
        }

        @Override
        public R getRight() {
            return null;
        }

        @Override
        public Triple<L, M, R> setLeft(L left) {
            return null;
        }

        @Override
        public Triple<L, M, R> setMiddle(M middle) {
            return null;
        }

        @Override
        public Triple<L, M, R> setRight(R right) {
            return null;
        }

        @Override
        public int compareTo(Triple<L, M, R> o) {
            return 0;
        }
    }
}
