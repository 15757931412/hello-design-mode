package cn.ppxytest.designmodewang.pay.strategy.context;

import cn.ppxytest.designmodewang.pojo.Order;

public abstract class AbstractPayContext {
    public abstract String execute(Order order);
}
