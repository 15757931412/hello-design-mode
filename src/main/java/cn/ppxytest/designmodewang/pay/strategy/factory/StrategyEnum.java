package cn.ppxytest.designmodewang.pay.strategy.factory;

public enum StrategyEnum {
    alipay("cn.ppxytest.designmodewang.pay.strategy.AlipayStrategy"),
    wechat("cn.ppxytest.designmodewang.pay.strategy.WechatStrategy");
    String value;
    StrategyEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}
