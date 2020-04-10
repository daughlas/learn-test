package tech.lvjiawen.httpClient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest() {
        // 默认读取 properties 类型，自动会去找 resource 下main的文件
        // 注意是 resource 下面的相对路径
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void getCookies() throws IOException {
        String result;
        String uri = bundle.getString("getCookies.uri");
        String testUrl = this.url + uri;
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        // 获取 cookies 信息
        this.store = client.getCookieStore();
        List<Cookie> cookieList = this.store.getCookies();

        for (Cookie cookie: cookieList) {
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookie name =" + name + ", cookie value = " + value);
        }
    }

    @Test(dependsOnMethods = {"getCookies"})
    public void testPostMethod() throws IOException {
        String uri = this.url + this.bundle.getString("test.post.with.cookies");

        // TODO 声明一个 post 方法
        // TODO 添加参数
        // TODO 设置请求头
        // TODO 设置请求信息
        // TODO 声明 client，执行方法
        // TODO 存储响应结果

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(uri);
        JSONObject param = new JSONObject();
        param.put("name", "lvjiawen");
        param.put("age", "18");
        post.setHeader("content-type", "application/json");
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);

        String result;
        client.setCookieStore(this.store);
        HttpResponse response = client.execute(post);
        result = EntityUtils.toString(response.getEntity());
        JSONObject resultJSON = new JSONObject(result);
        String success = (String) resultJSON.get("lvjiawen");
        String status = (String) resultJSON.get("status");
        Assert.assertEquals("success", success);
        Assert.assertEquals("1", status);
    }
}
