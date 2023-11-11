package cn.ppxytest.designmodewang.controller;

import cn.ppxytest.designmodewang.pojo.BusinessLaunch;
import cn.ppxytest.designmodewang.pojo.UserInfo;
import cn.ppxytest.designmodewang.adapter.Login3rdAdapter;
import cn.ppxytest.designmodewang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private Login3rdAdapter login3rdAdapter;

    @PostMapping("/business/launch")
    public List<BusinessLaunch> filterBusinessLaunch(@RequestParam("city") String city,
                                                     @RequestParam("sex") String sex,
                                                     @RequestParam("product") String product) {
        return userService.filterBusinessLaunch(city, sex, product);
    }

    @PostMapping("/ticket")
    public Object createTicket(String type, String productId, String content, String title, String bankInfo, String taxId) {
        return userService.createTicket(type, productId, content, title,bankInfo, taxId);

    }
    @PostMapping("/login")
    public String login(String account, String password) {
        return userService.login(account, password);
    }

    @PostMapping("/register")
    public String register(@RequestBody UserInfo userInfo) {
        return userService.register(userInfo);
    }

    @GetMapping("/gitee")
    public String gitee(String code, String state) {
        return login3rdAdapter.loginByGitee(code, state);
    }
}
