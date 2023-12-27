package cn.ppxytest.designmodewang.bridge.function;

import cn.ppxytest.designmodewang.pojo.UserInfo;
import cn.ppxytest.designmodewang.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;

public abstract class AbstractReisterLoginFunc implements RegisterLoginFuncInterface {
    protected String commonLogin(String account, String password, UserRepository userRepository) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo != null) {
            return "account / password Error!";
        }
        return "Login Success";
    }

    protected String commonRegister(UserInfo userInfo, UserRepository userRepository) {
        if (commonCheckUserExists(userInfo.getUserName(), userRepository)) {
            throw new RuntimeException("User already registered.");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "Register Success!";
    }

    protected boolean commonCheckUserExists(String username, UserRepository userRepository) {
        UserInfo user = userRepository.findByUserName(username);
        return user != null;
    }

    @Override
    public String login(String account, String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String register(UserInfo userInfo) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean checkUserExists(String userName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String login3rd(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }
}
