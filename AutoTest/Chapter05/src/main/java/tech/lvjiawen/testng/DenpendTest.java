package tech.lvjiawen.testng;

import org.testng.annotations.Test;

public class DenpendTest {
    @Test
    public void test1() {
        System.out.println("test 1 run");
        throw new RuntimeException();
    }

    @Test(dependsOnMethods = {"test1"})
    public void test2() {
        System.out.println("test 2 run");
    }
}
