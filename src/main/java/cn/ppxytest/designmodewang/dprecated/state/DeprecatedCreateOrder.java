package cn.ppxytest.designmodewang.dprecated.state;

import cn.ppxytest.designmodewang.dprecated.DeprecatedConstants;
import cn.ppxytest.designmodewang.util.RedisCommonProcessor;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeprecatedCreateOrder extends DeprecatedAbstractOrderState{

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    protected DeprecatedOrder createOrder(String orderId, String productId) {
        DeprecatedOrder order = DeprecatedOrder.builder()
                .orderId(orderId)
                .productId(productId)
                .state(ORDER_WAIT_PAY)
                .build();

        redisCommonProcessor.set(orderId,order,900);

        super.notifyObserver(orderId,ORDER_WAIT_PAY);
        return order;
    }
}
