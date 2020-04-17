package tech.lvjiawen.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tech.lvjiawen.model.User;


@RestController
@RequestMapping("v1")
public class Demo {
    // 起动机加载
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    public int getUserCount(){
        return template.selectOne("getUserCount");
    }

    @RequestMapping(value="addUser", method = RequestMethod.POST)
    public int addUser(@RequestBody User user) {
        return template.insert("addUser", user);
    }
}
