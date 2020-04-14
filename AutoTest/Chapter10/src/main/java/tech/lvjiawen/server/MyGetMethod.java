package tech.lvjiawen.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value="/", description = "这是我全部的get 方法")
public class MyGetMethod {

    @RequestMapping(value="/getCookies", method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到 Cookies", httpMethod = "GET")
    public String getCookies(HttpServletResponse response) {
        // HttpServletRequest 装请求信息
        // HttpServletResponse 响应信息
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        return "恭喜你，获得 cookies 成功";
    }

    /**
     * 要求客户端携带 cookies 访问
     */
    @RequestMapping(value="/get/with/cookies", method = RequestMethod.GET)
    @ApiOperation(value = "要求客户端携带 cookies 访问", httpMethod = "GET")
    public String getWithCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return "你必须携带 cookies 信息来";
        }
        for(Cookie cookie: cookies) {
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")) {
                return "恭喜你，访问成功，cookies 收到了";
            }
        }
        return "你必须携带 cookies 信息来";
    };

    /**
     * 携带参数的 get 请求。
     * query 形式传参
     * 模拟获取商品列表
     */
    @RequestMapping(value="/get/with/param", method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数的 get 请求1", httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam Integer start,
                                       @RequestParam Integer end) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 400);
        myList.put("衬衫", 58);
        myList.put("短裤", 48);

        return myList;
    }
    /**
     * get请求携带参数
     * 路径传参 /getDetail/:id 这种形式
     */
    @RequestMapping(value="/get/with/param/{start}/{end}", method = RequestMethod.GET)
    @ApiOperation(value="需要携带参数才能访问的 get 请求2", httpMethod = "GET")
    public Map myGetList(@PathVariable Integer start,
                         @PathVariable Integer end) {
        Map<String, Integer> myList = new HashMap<>();
        myList.put("鞋", 400);
        myList.put("衬衫", 500);
        myList.put("短裤", 600);

        return myList;
    }
}
