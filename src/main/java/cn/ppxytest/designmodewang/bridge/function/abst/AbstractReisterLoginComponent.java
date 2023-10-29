package cn.ppxytest.designmodewang.bridge.function.abst;

import cn.ppxytest.designmodewang.bridge.function.RegisterLoginFuncInterface;
import cn.ppxytest.designmodewang.pojo.UserInfo;
import jakarta.servlet.http.HttpServletRequest;

public abstract class AbstractReisterLoginComponent {
    protected RegisterLoginFuncInterface funcInterface;

    public AbstractReisterLoginComponent(RegisterLoginFuncInterface funcInterface){
        validate(funcInterface);
        this.funcInterface = funcInterface;
    }

    protected final void validate(RegisterLoginFuncInterface funcInterface) {
        if (!(funcInterface instanceof RegisterLoginFuncInterface)){
            throw new UnsupportedOperationException("Unknown register login function type");
        }
    }
    public abstract String login(String username, String password);

    public abstract String register(UserInfo userInfo);

    protected abstract boolean checkUserExists(String username);

    public abstract String login3rd(HttpServletRequest request);
}
