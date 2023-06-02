package top.youlanqiang.toolbox.basic;

import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.Toolbox;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

/**
 * @author youlanqiang
 *         created in 2022/12/02 22:18
 */
@DisplayName("EqualsHepler测试类")
public class EqualsHeplerTest {

    /**
     * InnerEqualsHeplerTest
     */
    public record Person(String name, int age) {
    }

    // @DisplayName("比较相同对象")
    // @Test
    // public void testSameObjects() {
    // Person tom = new Person("tom", 18);
    // Person tom2 = new Person("tom", 18);
    // boolean equalsValue = Toolbox.equalsHepler()
    // .addCondition(tom.name(), tom2.name())
    // .addCondition(tom.age(), tom2.age())
    // .doEquals();
    // assertTrue(equalsValue);
    // }

    // @DisplayName("比较不同对象")
    // @Test
    // public void testDiffObjects() {
    // Person tom = new Person("tom", 18);
    // Person tom2 = new Person("tom", 22);
    // boolean equalsValue = Toolbox.equalsHepler()
    // .addCondition(tom.name(), tom2.name())
    // .addCondition(tom.age(), tom2.age())
    // .doEquals();
    // assertFalse(equalsValue);
    // }

}
