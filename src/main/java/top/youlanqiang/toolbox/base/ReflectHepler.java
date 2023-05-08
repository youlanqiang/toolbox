package top.youlanqiang.toolbox.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 反射对象帮助类
 * 
 * @author youlanqiang
 */
public final class ReflectHepler {

    /**
     * 获取符合JavaBean规范的get方法
     * 
     * @param target 目标类
     * @return key为属性名称 object为返回值
     */
    public static Map<String, Object> getPublicGetterMap(Object target) {

        var methods = filterMethods(target.getClass().getDeclaredMethods(), "get", true);
        if (methods.isEmpty()) {
            return Collections.emptyMap();
        }
        var result = new HashMap<String, Object>(methods.size());

        methods.forEach(method -> {

            String name = resolveFieldNameByMethod(method);
            try {
                result.put(name, method.invoke(target, null));
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    public static List<Method> filterMethods(Method[] methods, String prefix, boolean nonParameter) {
        if (ObjectHepler.isEmpty(methods)) {
            return Collections.emptyList();
        }

        var result = new ArrayList<Method>(methods.length);
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if (method.getName().startsWith(prefix)) {
                if (nonParameter && method.getParameterCount() == 0) {
                    result.add(method);
                } else {
                    result.add(method);
                }
            }
        }
        return result;
    }

    /**
     * 解析方法的属性名称
     * 
     * @param method 方法
     * @return 去掉方法的get,set前缀并将后一个字符改为小写
     */
    public static String resolveFieldNameByMethod(Method method) {
        String methodName = method.getName();
        if (methodName.startsWith("get") || methodName.startsWith("set")) {
            return StringHepler.lowerFirst(methodName.substring(3));
        }
        return methodName;
    }
}
