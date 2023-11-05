package cn.ppxytest.designmodewang.service;

import cn.ppxytest.designmodewang.ordermanagement.state.OrderState;
import cn.ppxytest.designmodewang.ordermanagement.state.OrderStateChangeAction;
import cn.ppxytest.designmodewang.pojo.Order;
import cn.ppxytest.designmodewang.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;
    @Autowired
    private StateMachinePersister<OrderState, OrderStateChangeAction, String> stateMachineRedisPersister;
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    public Order createOrder(String productId) {
        String orderId = "OID" + productId;
        Order order = Order.builder().orderId(orderId).productId(productId).orderState(OrderState.ORDER_WAIT_PAY).build();
        redisCommonProcessor.set(order.getOrderId(), order, 900);
        return order;
    }

    public Order pay(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder.withPayload(OrderStateChangeAction.PAY_ORDER).setHeader("order", order).build();
        if (changeStateAction(message, order)) {
            return order;
        }
        return null;
    }

    private boolean changeStateAction(Message<OrderStateChangeAction> message, Order order) {
        try {
            stateMachineRedisPersister.restore(orderStateMachine, order.getOrderId() + "STATE");
            orderStateMachine.start();
            boolean res = orderStateMachine.sendEvent(message);
            stateMachineRedisPersister.persist(orderStateMachine, order.getOrderId() + "STATE");
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            orderStateMachine.stop();
        }
    }

    public Order send(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder.withPayload(OrderStateChangeAction.SEND_ORDER).setHeader("order", order).build();
        if (changeStateAction(message, order)) {
            return order;
        }

        return null;
    }

    public Order receive(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder.withPayload(OrderStateChangeAction.RECEIVE_ORDER).setHeader("order", order).build();
        if (changeStateAction(message, order)) {
            return order;
        }

        return null;
    }
}
