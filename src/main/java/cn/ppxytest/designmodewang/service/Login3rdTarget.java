package cn.ppxytest.designmodewang.service;

public interface Login3rdTarget {
    public String loginByGitee(String code, String state);

    public String loginByWechat(String code);

    String LoginByQQ(String code);
}
