package tech.lvjiawen.testng;

import org.testng.annotations.Test;


public class TimeOutTest {
    @Test(timeOut = 3000) // 单位为ms
    public void testSuccess() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Test(timeOut = 3000) // 单位为ms
    public void testFailed() throws InterruptedException {
        Thread.sleep(4000);
    }
}
