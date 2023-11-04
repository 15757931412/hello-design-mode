package cn.ppxytest.designmodewang.dprecated.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeprecatedOrderContext {
    @Autowired
    private DeprecatedCreateOrder deprecatedCreateOrder;
    @Autowired
    private DeprecatedPayOrder deprecatedPayOrder;
    @Autowired
    private DeprecatedSendOrder deprecatedSendOrder;
    @Autowired
    private DeprecatedReceiveOrder deprecatedReceiveOrder;

    public DeprecatedOrder createOrder(String orderId, String productId) {
        return deprecatedCreateOrder.createOrder(orderId, productId);
    }

    public DeprecatedOrder payOrder(String orderId) {
        return deprecatedPayOrder.payOrder(orderId);
    }

    public DeprecatedOrder sendOrder(String orderId) {
        return deprecatedSendOrder.sendOrder(orderId);
    }

    public DeprecatedOrder receiveOrder(String orderId) {
        return deprecatedReceiveOrder.receiveOrder(orderId);
    }

}
