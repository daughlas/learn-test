package tech.lvjiawen.testng;

import org.testng.annotations.Test;

public class IgnoreTest {
    @Test
    public void testCase1() {
        System.out.println("这是测试用例1");
    }

    @Test(enabled = false)
    public void testCase2() {
        System.out.println("这是测试用例2");
    }
}
