package top.youlanqiang.toolbox.basic;


public final class Objects {


    public static EqualsHepler buildEqualsHepler(Object source, Object target){
        return new EqualsHepler(source, target);
    }
    
}
