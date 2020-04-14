package tech.lvjiawen.server;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import tech.lvjiawen.bean.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value="/", description = "这是我全部的 post 请求")
@RequestMapping("/v1")
public class MyPostMehod {
    private static Cookie cookie;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value="登录接口，成功后获取 cookies 信息", httpMethod = "POST")
    public String login(HttpServletResponse response,
                        @RequestParam(value="userName", required=true) String userName,
                        @RequestParam(value="password", required=true) String password) {
        if (userName.equals("lvjiawen") && password.equals("123456")) {
            cookie = new Cookie("login", "true");
            response.addCookie(cookie);
            return "恭喜你登录成功了";
        }
        return "用户名或者密码错误";
    }

    @RequestMapping(value="/getUserList", method = RequestMethod.POST)
    @ApiOperation(value="获取用户列表", httpMethod = "POST")
    public String getUserList(HttpServletRequest request,
                            @RequestBody User u) {
        User user = new User();
        // 获取 cookies
        Cookie[] cookies = request.getCookies();
        // 验证 cookies
        for(Cookie c: cookies) {
            if (c.getName() == "login"
                    && c.getValue() == "true"
                    && u.getUserName() == "lvjiawen"
                    && u.getPassword() == "123456"
            ) {
                user.setName("lisi");
                user.setAge("18");
                user.setSex("male");
                return user.toString();
            }
        }
        return "参数不合法";
    }

}
