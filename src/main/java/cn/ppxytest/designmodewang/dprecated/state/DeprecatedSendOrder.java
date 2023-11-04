package cn.ppxytest.designmodewang.dprecated.state;

import cn.ppxytest.designmodewang.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeprecatedSendOrder extends DeprecatedAbstractOrderState{

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Override
    protected DeprecatedOrder sendOrder(String orderId) {
        DeprecatedOrder order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_SEND)){
            throw new UnsupportedOperationException("Order state should be ORDER_WAIT_SEND. But now it is state is : " + order.getState());
        }
        order.setState(ORDER_WAIT_PECEIVE);
        redisCommonProcessor.set(orderId,order);
        return order;
    }
}
