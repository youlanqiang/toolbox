/**
 * toolbox是一个轻量java类库，jdk最低要求为17，封装了一些jdk没有提供的常用方法和实用类
 * 
 * @see <a href="https://github.com/youlanqiang/toolbox">源码地址</a>
 * @author youlanqiang
 * 
 */
module toolbox {
    requires java.base;

    exports top.youlanqiang.toolbox;
    exports top.youlanqiang.toolbox.base;
    exports top.youlanqiang.toolbox.collection;
    exports top.youlanqiang.toolbox.concurrent;
    exports top.youlanqiang.toolbox.resource.properties;
}
