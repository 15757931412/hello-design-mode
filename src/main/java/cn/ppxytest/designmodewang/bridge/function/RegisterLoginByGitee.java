package cn.ppxytest.designmodewang.bridge.function;

import cn.ppxytest.designmodewang.pojo.UserInfo;
import cn.ppxytest.designmodewang.repo.UserRepository;
import cn.ppxytest.designmodewang.util.HttpClientUtils;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RegisterLoginByGitee extends AbstractReisterLoginFunc implements RegisterLoginFuncInterface {

    @Value("${gitee.state}")
    private String giteeState;
    @Value("${gitee.token.url}")
    private String giteeTokenUrl;
    @Value("${gitee.user.url}")
    private String giteeUserUrl;
    @Value("${gitee.user.prefix}")
    private String giteeUserPrefix;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login3rd(HttpServletRequest request) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (!giteeState.equals(state)) {
            throw new UnsupportedOperationException("Invalid state");
        }
        String tokenUrl = giteeTokenUrl.concat(code);
        JSONObject tokenResponse = HttpClientUtils.execute(tokenUrl, HttpMethod.POST);
        String token = String.valueOf(tokenResponse.get("access_token"));
        String userUrl = giteeUserUrl.concat(token);
        JSONObject userInfoResponse = HttpClientUtils.execute(userUrl, HttpMethod.GET);

        String userName = giteeUserPrefix.concat(String.valueOf(userInfoResponse.get("name")));
        String password = userName;

        return autoRegister3rdAndLogin(userName, password);
    }

    private String autoRegister3rdAndLogin(String userName, String password) {
        if (commonCheckUserExists(userName,userRepository)) {
            return commonLogin(userName, password,userRepository);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserPassword(password);
        userInfo.setCreateDate(new Date());

        commonRegister(userInfo,userRepository);
        return login(userName, password);
    }


}
