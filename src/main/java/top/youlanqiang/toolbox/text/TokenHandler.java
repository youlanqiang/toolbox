package top.youlanqiang.toolbox.text;

/**
 * Token记号处理器
 * 
 * @author youlanqiang
 */
@FunctionalInterface
public interface TokenHandler {

    String handleToken(StringToken content);
}
