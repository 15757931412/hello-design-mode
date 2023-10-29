package cn.ppxytest.designmodewang.bridge.function;

import cn.ppxytest.designmodewang.bridge.function.abst.RegisterLoginComponentFactory;
import cn.ppxytest.designmodewang.pojo.UserInfo;
import cn.ppxytest.designmodewang.repo.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RegisterLoginByDefault extends AbstractReisterLoginFunc{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String account, String password) {
        return commonLogin(account, password, userRepository);
    }

    @Override
    public String register(UserInfo userInfo) {
        return commonRegister(userInfo, userRepository);
    }

    @Override
    public boolean checkUserExists(String userName) {
        return commonCheckUserExists(userName, userRepository);
    }

    @PostConstruct
    private void initFuncMap(){
        RegisterLoginComponentFactory.funcMap.put("GITEE", this);
    }
}
