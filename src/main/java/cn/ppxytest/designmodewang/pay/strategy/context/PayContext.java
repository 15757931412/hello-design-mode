package cn.ppxytest.designmodewang.pay.strategy.context;

import cn.ppxytest.designmodewang.pay.strategy.PayStrategyInterface;
import cn.ppxytest.designmodewang.pojo.Order;

public class PayContext extends AbstractPayContext{
    private PayStrategyInterface payStrategy;

    public PayContext(PayStrategyInterface payStrategy) {
        this.payStrategy = payStrategy;
    }

    @Override
    public String execute(Order order) {
        return this.payStrategy.pay(order);
    }
}
