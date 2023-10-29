package cn.ppxytest.designmodewang.service;

import org.springframework.stereotype.Component;

@Component
public class Login3rdAdapter extends UserService implements Login3rdTarget{


    @Override
    public String loginByGitee(String code, String state) {
        return null;
    }

    @Override
    public String loginByWechat(String code) {
        return null;
    }

    @Override
    public String LoginByQQ(String code) {
        return null;
    }
}
