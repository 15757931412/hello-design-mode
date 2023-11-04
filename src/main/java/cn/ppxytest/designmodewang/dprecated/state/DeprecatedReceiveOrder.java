package cn.ppxytest.designmodewang.dprecated.state;

import cn.ppxytest.designmodewang.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeprecatedReceiveOrder extends DeprecatedAbstractOrderState {
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    protected DeprecatedOrder receiveOrder(String orderId) {
        DeprecatedOrder order = (DeprecatedOrder) redisCommonProcessor.get(orderId);
        if (!order.getState().equals(ORDER_WAIT_PECEIVE)) {
            throw new UnsupportedOperationException("Order state should be ORDER_WAIT_RECEIVE. But it s is state is :" + order.getState());
        }
        order.setState(ORDER_FINISH);
        redisCommonProcessor.remove(orderId);

        super.notifyObserver(orderId,ORDER_FINISH);
        return order;
    }
}
