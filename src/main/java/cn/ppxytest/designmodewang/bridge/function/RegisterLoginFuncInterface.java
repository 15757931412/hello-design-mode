package cn.ppxytest.designmodewang.bridge.function;

import cn.ppxytest.designmodewang.pojo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

public interface RegisterLoginFuncInterface {
    String login(String account, String password);

    String register(UserInfo userInfo);

    boolean checkUserExists(String userName);

    String login3rd(HttpServletRequest request);
}
