package cn.ppxytest.designmodewang.pay.facade;

import cn.ppxytest.designmodewang.pay.strategy.context.PayContext;
import cn.ppxytest.designmodewang.pay.strategy.factory.PayContextFactory;
import cn.ppxytest.designmodewang.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PayFacade {
    @Autowired
    private PayContextFactory contextFactory;
    public String pay(Order order, Integer payType) {
        PayContext context = contextFactory.getContext(payType);
        return context.execute(order);
    }
}
