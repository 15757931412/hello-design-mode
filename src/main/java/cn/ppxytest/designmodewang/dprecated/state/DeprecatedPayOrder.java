package cn.ppxytest.designmodewang.dprecated.state;

import cn.ppxytest.designmodewang.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeprecatedPayOrder extends DeprecatedAbstractOrderState {
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    protected DeprecatedOrder payOrder(String orderId) {
        DeprecatedOrder order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_PAY)) {
            throw new UnsupportedOperationException("Order state should be ORDER_WAIT_PAY. But now it s state is : " + order.getState());
        }

        order.setState(ORDER_WAIT_SEND);
        redisCommonProcessor.set(orderId,order);

        super.notifyObserver(orderId,ORDER_WAIT_SEND);
        return order;
    }
}
