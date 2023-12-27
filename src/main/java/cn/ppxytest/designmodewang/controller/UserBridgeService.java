package cn.ppxytest.designmodewang.controller;

import cn.ppxytest.designmodewang.bridge.function.abst.AbstractReisterLoginComponent;
import cn.ppxytest.designmodewang.bridge.function.abst.RegisterLoginComponentFactory;
import cn.ppxytest.designmodewang.pojo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class UserBridgeService {
    public String login(String account, String password) {
        AbstractReisterLoginComponent registerLoginComponent = RegisterLoginComponentFactory.getComponent("DEFAULT");
        return registerLoginComponent.login(account, password);
    }

    public String register(UserInfo userInfo) {
        AbstractReisterLoginComponent component = RegisterLoginComponentFactory.getComponent("DEFAULT");
        return component.register(userInfo);
    }

    public String login3rd(HttpServletRequest request, String type) {
        AbstractReisterLoginComponent component = RegisterLoginComponentFactory.getComponent(type);
        return component.login3rd(request);
    }

}
