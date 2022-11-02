package top.youlanqiang.toolbox.basic;


public class EqualsHepler {

    private Object source;

    private Object target;

    private boolean equalsValue = true;


    public EqualsHepler(Object source, Object target){
        if(source == null){
            throw new IllegalArgumentException("source object is null");
        }
        this.source = source;
        this.target = target;
    }

    public EqualsHepler addEqualExp(Object v1, Object v2){
        if(!equalsValue){
            return this;
        }
        this.equalsValue = v1.equals(v2);
        return this;
    }

    public boolean doEquals(){
        if(source == target){
            return true;
        }
        if(target == null || source.getClass() != target.getClass()){
            return false;
        }
        return equalsValue;
    }


    
}
