package cn.ppxytest.designmodewang.bridge.function.abst;

import cn.ppxytest.designmodewang.bridge.function.RegisterLoginFuncInterface;
import cn.ppxytest.designmodewang.pojo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterLoginComponent extends AbstractReisterLoginComponent{
    public RegisterLoginComponent(RegisterLoginFuncInterface funcInterface) {
        super(funcInterface);
    }

    @Override
    public String login(String username, String password) {
        return funcInterface.login(username, password);
    }

    @Override
    public String register(UserInfo userInfo) {
        return funcInterface.register(userInfo);
    }

    @Override
    protected boolean checkUserExists(String username) {
        return funcInterface.checkUserExists(username);
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        return funcInterface.login3rd(request);
    }
}
