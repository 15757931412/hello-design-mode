package cn.ppxytest.designmodewang.controller;

import cn.ppxytest.designmodewang.adapter.Login3rdAdapter;
import cn.ppxytest.designmodewang.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bridge")
public class UserBridgeController {

    @Autowired
    private UserBridgeService userBridgeService;
    @Autowired
    private Login3rdAdapter login3rdAdapter;

    @PostMapping("/login")
    public String login(String account, String password) {
        return userBridgeService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userBridgeService.register(userInfo);
    }

    @GetMapping("/gitee")
    public String gitee(String code,String state){
        return login3rdAdapter.loginByGitee(code, state);
    }
}
