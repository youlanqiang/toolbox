package top.youlanqiang.toolbox.basic;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import top.youlanqiang.toolbox.Toolbox;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author youlanqiang
 * created in 2022/12/02 22:18
 */
public class EqualsHeplerTest {

    /**
     * InnerEqualsHeplerTest
     */
    public record Person(String name, int age) {
    }


    @Tag("比较相同对象")
    @Test
    public void testForSameObjects(){
        Person tom = new Person("tom", 18);
        Person tom2 = new Person("tom", 18);
        boolean equalsValue = Toolbox.equalsHepler()
            .addCondition(tom.name(), tom2.name())
            .addCondition(tom.age(), tom2.age())
            .doEquals();
        assertTrue(equalsValue);
    }

    @Tag("比较不同对象")
    @Test
    public void testForDiffObjects(){
        Person tom = new Person("tom", 18);
        Person tom2 = new Person("tom", 22);
        boolean equalsValue = Toolbox.equalsHepler()
            .addCondition(tom.name(), tom2.name())
            .addCondition(tom.age(), tom2.age())
            .doEquals();
        assertFalse(equalsValue);
    }





}
