package cn.ppxytest.designmodewang.dutychain.builder;

/**
 * @author: 水自强
 * @create-date: 2023/11/10 21:29
 */
public enum HandlerEnum {
    city("cn.ppxytest.designmodewang.dutychain.CityHandler"),
    sex("cn.ppxytest.designmodewang.dutychain.SexHandler"),
    product("cn.ppxytest.designmodewang.dutychain.ProductHandler");

    String value = "";

    HandlerEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
