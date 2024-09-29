/**
 * 项目名称:  restful-spring-boot-starter
 * 公司名称:  YiShoTech
 * All rights Reserved, Designed By YiShoTech 2023-2024
 */
package cn.yishotech.starter.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>类路径:cn.yishotech.starter.utils.AssertUtil</p>
 * <p>类描述:断言工具</p>
 * <p>创建人:jason zong</p>
 * <p>创建时间:2024/09/29 20:03</p>
 */
public class AssertUtil {


    /**
     * 检查给定对象是否为null，如果为null则抛出IllegalArgumentException，附带提供的错误消息。
     *
     * @param object  待检查的对象，任何类型
     * @param message 当object为null时，需要抛出的IllegalArgumentException异常消息
     * @throws IllegalArgumentException 如果对象为null，将根据message抛出此异常
     */
    public static void notNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }


    /**
     * 对象不为 null, 否则使用Supplier提供的消息抛出异常
     *
     * @param object   待检查的对象
     * @param supplier 提供异常消息的Supplier，当对象为null时调用
     */
    public static void notNull(Object object, Supplier<?> supplier) {
        if (Objects.isNull(object)) {
            supplier.get();
        }
    }


    /**
     * 断言对象为null，如果不为null则抛出IllegalArgumentException异常。
     *
     * @param object  待检查的对象，期望值为null
     * @param message 当object非空时，需要抛出的IllegalArgumentException异常消息
     * @throws IllegalArgumentException 如果对象不为null，将根据message抛出此异常
     */
    public static void isNull(Object object, String message) {
        if (Objects.nonNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言对象为null，如果不为null则抛出IllegalArgumentException异常。
     *
     * @param object   待检查的对象，期望值为null
     * @param supplier 当object非空时，需要抛出的IllegalArgumentException异常消息
     * @throws IllegalArgumentException 如果对象不为null，将根据message抛出此异常
     */
    public static void isNull(Object object, Supplier<?> supplier) {
        if (Objects.nonNull(object)) {
            supplier.get();
        }
    }

    /**
     * 断言布尔表达式为 true, 如果为 false 则抛出 IllegalArgumentException 异常
     *
     * @param expression 布尔表达式
     * @param message    异常消息内容，当断言失败时使用
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言布尔表达式为 true, 如果为 false 则抛出 IllegalArgumentException 异常
     *
     * @param expression 布尔表达式
     * @param supplier   异常方法，当断言失败时使用
     */
    public static void isTrue(boolean expression, Supplier<?> supplier) {
        if (!expression) {
            supplier.get();
        }
    }


    /**
     * 断言布尔表达式为 false, 如果为 true 则抛出 IllegalArgumentException 异常
     *
     * @param expression 布尔表达式，期望值为 false
     * @param message    异常消息内容，当断言失败时使用
     */
    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言布尔表达式为 false, 如果为 true 则抛出 IllegalArgumentException 异常
     *
     * @param expression 布尔表达式，期望值为 false
     * @param supplier   异常方法，当断言失败时使用
     */
    public static void isFalse(boolean expression, Supplier<?> supplier) {
        if (expression) {
            supplier.get();
        }
    }


    /**
     * 检查两个对象是否相等，如果不相等则抛出异常。
     * 使用 {@link Objects#equals(Object, Object)} 进行比较。
     *
     * @param obj1    第一个对象用于比较
     * @param obj2    第二个对象用于比较
     * @param message 当对象不相等时抛出的异常消息内容
     */
    public static void equals(Object obj1, Object obj2, String message) {
        if (!Objects.equals(obj1, obj2)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 检查两个对象是否相等，如果不相等则抛出异常。
     * 使用 {@link Objects#equals(Object, Object)} 进行比较。
     *
     * @param obj1     第一个对象用于比较
     * @param obj2     第二个对象用于比较
     * @param supplier 当对象不相等时抛出的异常消息内容
     */
    public static void equals(Object obj1, Object obj2, Supplier<?> supplier) {
        if (!Objects.equals(obj1, obj2)) {
            supplier.get();
        }
    }

    /**
     * 断言两个对象不相等，如果相等则抛出异常。
     * 使用 {@link Objects#equals(Object, Object)} 进行比较。
     *
     * @param obj1    第一个对象用于比较
     * @param obj2    第二个对象用于比较
     * @param message 当对象相等时抛出的异常消息内容
     */
    public static void notEquals(Object obj1, Object obj2, String message) {
        if (Objects.equals(obj1, obj2)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言两个对象不相等，如果相等则抛出异常。
     * 使用 {@link Objects#equals(Object, Object)} 进行比较。
     *
     * @param obj1     第一个对象用于比较
     * @param obj2     第二个对象用于比较
     * @param supplier 当对象相等时抛出的异常消息内容
     */
    public static void notEquals(Object obj1, Object obj2, Supplier<?> supplier) {
        if (Objects.equals(obj1, obj2)) {
            supplier.get();
        }
    }


    /**
     * 断言num1大于num2，如果不满足条件则抛出IllegalArgumentException异常。
     *
     * @param num1    被比较的第一个数字
     * @param num2    被比较的第二个数字
     * @param message 当num1不大于num2时抛出的异常消息内容
     */
    public static void greaterThan(long num1, long num2, String message) {
        if (num1 <= num2) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言num1大于num2，如果不满足条件则抛出IllegalArgumentException异常。
     *
     * @param num1     被比较的第一个数字
     * @param num2     被比较的第二个数字
     * @param supplier 当num1不大于num2时抛出的异常消息内容
     */
    public static void greaterThan(long num1, long num2, Supplier<?> supplier) {
        if (num1 <= num2) {
            supplier.get();
        }
    }


    /**
     * 断言num1大于等于num2，如果不满足条件则抛出IllegalArgumentException异常。
     *
     * @param num1    被比较的第一个数字
     * @param num2    被比较的第二个数字
     * @param message 当num1小于num2时抛出的异常消息内容
     */
    public static void greaterThanOrEqual(long num1, long num2, String message) {
        if (num1 < num2) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言num1大于等于num2，如果不满足条件则抛出IllegalArgumentException异常。
     *
     * @param num1     被比较的第一个数字
     * @param num2     被比较的第二个数字
     * @param supplier 当num1小于num2时抛出的异常消息内容
     */
    public static void greaterThanOrEqual(long num1, long num2, Supplier<?> supplier) {
        if (num1 < num2) {
            supplier.get();
        }
    }


    /**
     * 断言num1小于num2，如果不满足条件则抛出IllegalArgumentException异常。
     *
     * @param num1    被比较的第一个数字
     * @param num2    被比较的第二个数字
     * @param message 当num1不小于num2时抛出的异常消息内容
     */
    public static void lessThan(long num1, long num2, String message) {
        if (num1 >= num2) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言num1小于num2，如果不满足条件则抛出IllegalArgumentException异常。
     *
     * @param num1     被比较的第一个数字
     * @param num2     被比较的第二个数字
     * @param supplier 当num1不小于num2时抛出的异常消息内容
     */
    public static void lessThan(long num1, long num2, Supplier<?> supplier) {
        if (num1 >= num2) {
            supplier.get();
        }
    }


    /**
     * 断言num1小于等于num2，如果不满足条件则抛出IllegalArgumentException异常。
     *
     * @param num1    被比较的第一个数字
     * @param num2    被比较的第二个数字
     * @param message 当num1大于num2时抛出的异常消息内容
     */
    public static void lessThanOrEqual(long num1, long num2, String message) {
        if (num1 > num2) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言num1小于等于num2，如果不满足条件则抛出IllegalArgumentException异常。
     *
     * @param num1     被比较的第一个数字
     * @param num2     被比较的第二个数字
     * @param supplier 当num1大于num2时抛出的异常消息内容
     */
    public static void lessThanOrEqual(long num1, long num2, Supplier<?> supplier) {
        if (num1 > num2) {
            supplier.get();
        }
    }


    /**
     * 断言字符串为空白，如果字符串不为空白则抛出IllegalArgumentException异常。
     * 空白指字符串为null、空字符串或只包含空白字符（如空格、制表符）。
     *
     * @param str     待检查的字符串
     * @param message 当字符串不为空白时抛出的异常消息内容
     */
    public static void isBlank(String str, String message) {
        if (StringUtils.isNotBlank(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言字符串为空白，如果字符串不为空白则抛出IllegalArgumentException异常。
     * 空白指字符串为null、空字符串或只包含空白字符（如空格、制表符）。
     *
     * @param str      待检查的字符串
     * @param supplier 当字符串不为空白时抛出的异常消息内容
     */
    public static void isBlank(String str, Supplier<?> supplier) {
        if (StringUtils.isNotBlank(str)) {
            supplier.get();
        }
    }

    /**
     * 断言字符串不为空白，如果字符串为空白则抛出IllegalArgumentException异常。
     * 非空白指字符串不为null、不为空字符串且不只包含空白字符（如空格、制表符）。
     *
     * @param str     待检查的字符串
     * @param message 当字符串为空白时抛出的异常消息内容
     */
    public static void isNotBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言字符串不为空白，如果字符串为空白则抛出IllegalArgumentException异常。
     * 非空白指字符串不为null、不为空字符串且不只包含空白字符（如空格、制表符）。
     *
     * @param str      待检查的字符串
     * @param supplier 当字符串为空白时抛出的异常消息内容
     */
    public static void isNotBlank(String str, Supplier<?> supplier) {
        if (StringUtils.isBlank(str)) {
            supplier.get();
        }
    }


    /**
     * 断言对象为空，如果不为空则抛出IllegalArgumentException异常。
     *
     * @param collection 待检查的集合
     * @param message    当对象不为空时抛出的异常消息内容
     */
    public static void isEmpty(Collection<?> collection, String message) {
        if (!collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言对象为空，如果不为空则抛出IllegalArgumentException异常。
     *
     * @param collection 待检查的集合
     * @param supplier   当对象不为空时抛出的异常消息内容
     */
    public static void isEmpty(Collection<?> collection, Supplier<?> supplier) {
        if (!collection.isEmpty()) {
            supplier.get();
        }
    }

    /**
     * 断言集合不为空，如果集合为null或为空，则抛出IllegalArgumentException异常。
     *
     * @param collection 待检查的集合
     * @param message    当集合为空时抛出的异常消息内容
     */
    public static void notEmpty(Collection<?> collection, String message) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言集合不为空，如果集合为null或为空，则抛出IllegalArgumentException异常。
     *
     * @param collection 待检查的集合
     * @param supplier   当集合为空时抛出的异常消息内容
     */
    public static void notEmpty(Collection<?> collection, Supplier<?> supplier) {
        if (Objects.isNull(collection) || collection.isEmpty()) {
            supplier.get();
        }
    }


    /**
     * 断言数组不为空，如果数组为null或长度为0，则抛出IllegalArgumentException异常。
     *
     * @param array   待检查的数组
     * @param message 当数组为空时抛出的异常消息内容
     */
    public static void notEmpty(Object[] array, String message) {
        if (Objects.isNull(array) || array.length == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言数组不为空，如果数组为null或长度为0，则抛出IllegalArgumentException异常。
     *
     * @param array    待检查的数组
     * @param supplier 当数组为空时抛出的异常消息内容
     */
    public static void notEmpty(Object[] array, Supplier<?> supplier) {
        if (Objects.isNull(array) || array.length == 0) {
            supplier.get();
        }
    }

    /**
     * 断言数组为空，如果数组不为null且长度不为0，则抛出IllegalArgumentException异常。
     *
     * @param array   待检查的数组
     * @param message 当数组非空时抛出的异常消息内容
     */
    public static void isEmpty(Object[] array, String message) {
        if (Objects.nonNull(array) && array.length != 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言数组为空，如果数组不为null且长度不为0，则抛出IllegalArgumentException异常。
     *
     * @param array    待检查的数组
     * @param supplier 当数组非空时抛出的异常消息内容
     */
    public static void isEmpty(Object[] array, Supplier<?> supplier) {
        if (Objects.nonNull(array) && array.length != 0) {
            supplier.get();
        }
    }


    /**
     * 断言Map不为空，如果Map为null或不含任何映射关系，则抛出IllegalArgumentException异常。
     *
     * @param map     待检查的Map
     * @param message 当Map为空时抛出的异常消息内容
     */
    public static void notEmpty(Map<?, ?> map, String message) {
        if (Objects.isNull(map) || map.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }


    /**
     * 断言Map不为空，如果Map为null或不含任何映射关系，则抛出IllegalArgumentException异常。
     *
     * @param map      待检查的Map
     * @param supplier 当Map为空时抛出的异常消息内容
     */
    public static void notEmpty(Map<?, ?> map, Supplier<?> supplier) {
        if (Objects.isNull(map) || map.isEmpty()) {
            supplier.get();
        }
    }

    /**
     * 断言Map为空，如果Map不为null且包含映射关系，则抛出IllegalArgumentException异常。
     *
     * @param map     待检查的Map
     * @param message 当Map非空时抛出的异常消息内容
     */
    public static void isEmpty(Map<?, ?> map, String message) {
        if (Objects.nonNull(map) && !map.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言Map为空，如果Map不为null且包含映射关系，则抛出IllegalArgumentException异常。
     *
     * @param map      待检查的Map
     * @param supplier 当Map非空时抛出的异常消息内容
     */
    public static void isEmpty(Map<?, ?> map, Supplier<?> supplier) {
        if (Objects.nonNull(map) && !map.isEmpty()) {
            supplier.get();
        }
    }


    /**
     * 断言字符串是否符合指定的正则表达式模式，如果不匹配则抛出IllegalArgumentException异常。
     *
     * @param input   待检查的字符串
     * @param pattern 正则表达式模式
     * @param message 当字符串不符合模式时抛出的异常消息内容
     */
    public static void matchesPattern(String input, String pattern, String message) {
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言字符串是否符合指定的正则表达式模式，如果不匹配则使用Supplier获取异常消息并抛出IllegalArgumentException异常。
     *
     * @param input    待检查的字符串
     * @param pattern  正则表达式模式
     * @param supplier 当字符串不符合模式时提供异常消息内容的Supplier
     */
    public static void matchesPattern(String input, String pattern, Supplier<?> supplier) {
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);
        if (!matcher.matches()) {
            supplier.get();
        }
    }


    /**
     * 断言集合包含指定元素，如果不包含则抛出IllegalArgumentException异常。
     *
     * @param collection 待检查的集合
     * @param element    需要检查的元素
     * @param message    当集合不包含元素时抛出的异常消息内容
     */
    public static <T> void contains(Collection<T> collection, T element, String message) {
        if (!collection.contains(element)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言集合包含指定元素，如果不包含则使用Supplier获取异常消息并抛出IllegalArgumentException异常。
     *
     * @param collection 待检查的集合
     * @param element    需要检查的元素
     * @param supplier   当集合不包含元素时提供异常消息内容的Supplier
     */
    public static <T> void contains(Collection<T> collection, T element, Supplier<?> supplier) {
        if (!collection.contains(element)) {
            supplier.get();
        }
    }

    /**
     * 断言Map包含指定键，如果不包含则抛出IllegalArgumentException异常。
     *
     * @param map     待检查的Map
     * @param key     需要检查的键
     * @param message 当Map不包含键时抛出的异常消息内容
     */
    public static <K, V> void containsKey(Map<K, V> map, K key, String message) {
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言Map包含指定键，如果不包含则使用Supplier获取异常消息并抛出IllegalArgumentException异常。
     *
     * @param map      待检查的Map
     * @param key      需要检查的键
     * @param supplier 当Map不包含键时提供异常消息内容的Supplier
     */
    public static <K, V> void containsKey(Map<K, V> map, K key, Supplier<?> supplier) {
        if (!map.containsKey(key)) {
            supplier.get();
        }
    }

    /**
     * 断言Map包含指定值，如果不包含则抛出IllegalArgumentException异常。
     *
     * @param map     待检查的Map
     * @param value   需要检查的值
     * @param message 当Map不包含值时抛出的异常消息内容
     */
    public static <K, V> void containsValue(Map<K, V> map, V value, String message) {
        if (!map.containsValue(value)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 断言Map包含指定值，如果不包含则使用Supplier获取异常消息并抛出IllegalArgumentException异常。
     *
     * @param map      待检查的Map
     * @param value    需要检查的值
     * @param supplier 当Map不包含值时提供异常消息内容的Supplier
     */
    public static <K, V> void containsValue(Map<K, V> map, V value, Supplier<?> supplier) {
        if (!map.containsValue(value)) {
            supplier.get();
        }
    }

}
