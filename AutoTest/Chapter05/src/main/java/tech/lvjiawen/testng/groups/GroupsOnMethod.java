package tech.lvjiawen.testng.groups;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {

    @Test(groups="server")
    public void test1() {
        System.out.println("服务组 1");
    }

    @Test(groups="server")
    public void test2() {
        System.out.println("服务组 2");
    }

    @Test(groups="client")
    public void test3() {
        System.out.println("客户组 3");
    }

    @Test(groups="client")
    public void test4() {
        System.out.println("客户组 4");
    }

    @BeforeGroups("server")
    public void beforeGroupOnServer() {
        System.out.println("服务组前");
    }

    @AfterGroups("server")
    public void afterGroupOnServer() {
        System.out.println("服务组后");
    }
}
