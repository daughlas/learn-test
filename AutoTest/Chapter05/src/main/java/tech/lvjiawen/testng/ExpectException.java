package tech.lvjiawen.testng;

import org.testng.annotations.Test;

public class ExpectException {
    /**
     * 什么时候用到异常测试
     * 在我们期望结果为一个异常的时候
     * 比如：传入了某些不合法的参数，程序跑出了异常
     * 也就是说我的预期结果就是这个异常
     */

    @Test(expectedExceptions=RuntimeException.class)
    public void runTimeExceptionFailed() {
        System.out.println("这是一个失败的异常测试");
    }

    @Test(expectedExceptions=RuntimeException.class)
    public void runTimeExceptionSuccess() {
        System.out.println("这是我的异常测试");
        throw new RuntimeException();

    }
}
