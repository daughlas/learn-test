package tech.lvjiawen.httpClient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {

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
    public void testGetWithCookies() throws IOException {
        String uri = this.url + bundle.getString("test.get.with.cookies");
        HttpGet get = new HttpGet(uri);
        DefaultHttpClient client = new DefaultHttpClient();
        // 设置 cookies 信息
        client.setCookieStore(this.store);
        HttpResponse response =  client.execute(get);

        // 获取相应的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("staus code = " + statusCode);

        if (statusCode == 200) {
            System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        }
    }
}
