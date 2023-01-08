package top.youlanqiang.toolbox.base;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

/**
 * 对象工具类
 * 
 * @author youlanqiang
 *         created in 2022/12/06 21:44
 * 
 */
public final class ObjectHepler {

    private ObjectHepler() {
    }

    /**
     * 集合是否为空
     * 
     * @param collection 集合对象
     * @return true or false
     */
    public static boolean isEmpty(Collection<Object> collection) {
        return collection == null || collection.size() == 0;
    }

    /**
     * Map是否为空
     * 
     * @param map map对象
     * @return true or false
     */
    public static boolean isEmpty(Map<Object, Object> map) {
        return map == null || map.size() == 0;
    }

    /**
     * 数组是否为空
     * 
     * @param objects 数组
     * @return true or false
     */
    public static boolean isEmpty(Object... objects) {
        return objects == null || objects.length == 0;
    }

    /**
     * 判断字符串是否为null
     * 字符串为"null",也会返回true
     * 
     * @param str 判断对象
     * @return true or false
     */
    public static boolean isNullString(CharSequence str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        if (str.toString().trim().equalsIgnoreCase("null")) {
            return true;
        }
        return false;

    }

    /**
     * 判断value对象是否为空
     * 
     * @param value 判断对象
     * @return 为空返回true
     */
    public static boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        }

        // 将估计最频繁使用的类型首先判断
        if (value instanceof CharSequence charSequence) {

            return charSequence.length() == 0;
        } else if (value instanceof Collection<?> collection) {

            return collection.isEmpty();
        } else if (value instanceof Map<?, ?> map) {

            return map.isEmpty();
        } else if (value instanceof Optional<?> optional) {

            return optional.isPresent();
        } else if (value instanceof OptionalInt optional) {

            return optional.isPresent();
        } else if (value instanceof OptionalLong optional) {

            return optional.isPresent();
        } else if (value instanceof OptionalDouble optional) {

            return optional.isPresent();
        } else if (value.getClass().isArray()) {

            return Array.getLength(value) == 0;
        }
        return false;
    }

    /**
     * 基本对象转换工具类
     */
    public enum ObjectCastHepler {
        INSTANCE;

        /**
         * Object转换为Byte字节(8位)
         * 
         * @throws ClassCastException 对象转换错误时抛出异常
         * @param val 对象
         * @return byte对象
         */
        public Byte castToByte(Object val) {
            if (val == null) {
                return null;
            }
            if (val instanceof Number number) {
                return number.byteValue();
            }
            if (val instanceof String str) {
                if (isEmpty(str)) {
                    return null;
                }
                if (str.contains(".")) {
                    str = str.substring(str.indexOf('.'));
                    return Byte.parseByte(str);
                }
                return Byte.parseByte(str);
            }
            throw new ClassCastException();
        }

        /**
         * Object转换为Short(16位)
         * 
         * @throws ClassCastException 对象转换错误时抛出异常
         * @param val 对象
         * @return short对象
         */
        public Short castToShort(Object val) {
            if (val == null) {
                return null;
            }
            if (val instanceof Number number) {
                return number.shortValue();
            }
            if (val instanceof String str) {
                if (isEmpty(str)) {
                    return null;
                }
                if (str.contains(".")) {
                    str = str.substring(str.indexOf('.'));
                    return Short.parseShort(str);
                }
                return Short.parseShort(str);
            }
            throw new ClassCastException();
        }

        /**
         * Object转换为Integer(32位)
         * 
         * @throws ClassCastException 对象转换错误时抛出异常
         * @param val 对象
         * @return integer对象
         */
        public Integer castToInteger(Object val) {
            if (val == null) {
                return null;
            }
            if (val instanceof Number number) {
                return number.intValue();
            }
            if (val instanceof String str) {
                if (isEmpty(str)) {
                    return null;
                }
                if (str.contains(".")) {
                    str = str.substring(str.indexOf('.'));
                    return Integer.valueOf(str);
                }
                return Integer.parseInt(str);
            }
            throw new ClassCastException();
        }

        /**
         * Object转换为Long(64位)
         * 
         * @throws ClassCastException 对象转换错误时抛出异常
         * @param val 对象
         * @return long对象
         */
        public Long castToLong(Object val) {
            if (val == null) {
                return null;
            }
            if (val instanceof Number number) {
                return number.longValue();
            }
            if (val instanceof String str) {
                if (isEmpty(str)) {
                    return null;
                }
                return Long.parseLong(str);
            }
            throw new ClassCastException();
        }

        /**
         * Object转换为Char(8位)
         * 
         * @throws ClassCastException 对象转换错误时抛出异常
         * @param val 对象
         * @return char对象
         */
        public Character castToCharacter(Object val) {
            if (val == null) {
                return null;
            }
            if (val instanceof Number number) {
                /**
                 * 将数字转换为ASCII码
                 */
                int intVal = number.intValue();
                if (intVal >= 0 && intVal <= 127) {
                    return (char) intVal;
                }
            }
            if (val instanceof Character character) {
                return character;
            }

            if (val instanceof CharSequence charSequence) {
                if (isNullString(charSequence)) {
                    return '\0';
                }
                /**
                 * 为避免发生歧义,不是char类型的对象还是直接抛出异常比较好
                 */
                if (charSequence.length() == 1) {
                    return charSequence.charAt(0);
                }
            }
            throw new ClassCastException();
        }

        /**
         * Object转换为float(32位)
         * 
         * @throws ClassCastException 对象转换错误时抛出异常
         * @param val 对象
         * @return float对象
         */
        public Float castToFloat(Object val) {
            if (val == null) {
                return null;
            }
            if (val instanceof Number number) {
                return number.floatValue();
            }
            if (val instanceof String str) {
                if (isEmpty(str)) {
                    return null;
                }
                return Float.parseFloat(str);
            }
            throw new ClassCastException();
        }

        /**
         * Object转换为double(64位)
         * 
         * @throws ClassCastException 对象转换错误时抛出异常
         * @param val 对象
         * @return double对象
         */
        public Double castToDouble(Object val) {
            if (val == null) {
                return null;
            }
            if (val instanceof Number number) {
                return number.doubleValue();
            }
            if (val instanceof String str) {
                if (isEmpty(str)) {
                    return null;
                }
                return Double.parseDouble(str);
            }
            throw new ClassCastException();
        }

        /**
         * Object转换为String
         * 
         * @param val 对象
         * @return string对象
         */
        public String castToString(Object val) {
            if (val == null) {
                return null;
            }
            return val.toString();
        }

        /**
         * Object转换为boolean(8位)
         * 
         * @throws ClassCastException 对象转换错误时抛出异常
         * @param val 对象
         * @return boolean对象
         */
        public Boolean castToBoolean(Object val) {
            if (val == null) {
                return null;
            }
            if (val instanceof Boolean bool) {
                return bool;
            }
            if (val instanceof Number number) {
                return number.intValue() != 0;
            }
            if (val instanceof String str) {
                if (isEmpty(str)) {
                    return null;
                }
                return Boolean.valueOf(str);
            }

            throw new ClassCastException();
        }

    }

}
