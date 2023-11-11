package cn.ppxytest.designmodewang.service;

import cn.ppxytest.designmodewang.ordermanagement.command.OrderCommand;
import cn.ppxytest.designmodewang.ordermanagement.command.invoker.OrderCommandInvoker;
import cn.ppxytest.designmodewang.ordermanagement.state.OrderState;
import cn.ppxytest.designmodewang.ordermanagement.state.OrderStateChangeAction;
import cn.ppxytest.designmodewang.pay.facade.PayFacade;
import cn.ppxytest.designmodewang.pojo.Order;
import cn.ppxytest.designmodewang.transaction.colleague.AbstractCustomer;
import cn.ppxytest.designmodewang.transaction.colleague.Buyer;
import cn.ppxytest.designmodewang.transaction.colleague.Payer;
import cn.ppxytest.designmodewang.transaction.mediator.Mediator;
import cn.ppxytest.designmodewang.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class OrderService implements OrderServiceInterface {
    @Autowired
    private StateMachine<OrderState, OrderStateChangeAction> orderStateMachine;
    @Autowired
    private StateMachinePersister<OrderState, OrderStateChangeAction, String> stateMachineRedisPersister;
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Autowired
    private OrderCommand orderCommand;
    @Autowired
    private PayFacade payFacade;
    @Autowired
    private Mediator mediator;

    @Override
    public Order createOrder(String productId) {
        String orderId = "OID" + productId;
        Order order = Order.builder().orderId(orderId).productId(productId).orderState(OrderState.ORDER_WAIT_PAY).build();
        OrderCommandInvoker invoker = new OrderCommandInvoker();
        invoker.invoke(orderCommand, order);
        redisCommonProcessor.set(order.getOrderId(), order, 900);
        return order;
    }

    @Override
    public Order pay(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder.withPayload(OrderStateChangeAction.PAY_ORDER).setHeader("order", order).build();
        if (changeStateAction(message, order)) {
            return order;
        }
        return null;
    }

    @Override
    public Order send(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder.withPayload(OrderStateChangeAction.SEND_ORDER).setHeader("order", order).build();
        if (changeStateAction(message, order)) {
            return order;
        }

        return null;
    }

    @Override
    public Order receive(String orderId) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        Message<OrderStateChangeAction> message = MessageBuilder.withPayload(OrderStateChangeAction.RECEIVE_ORDER).setHeader("order", order).build();
        if (changeStateAction(message, order)) {
            return order;
        }

        return null;
    }

    @Override
    public String getPayUrl(String orderId, Float price, Integer payType) {
        Order order = (Order) redisCommonProcessor.get(orderId);
        order.setPrice(price);
        return payFacade.pay(order, payType);
    }

    @Override
    public void friendPay(String sourceCustomer, String orderId, String targetCustomer, String payResult, String role) {
        Buyer buyer = new Buyer(orderId, mediator, sourceCustomer);
        Payer payer = new Payer(orderId, mediator, sourceCustomer);
        HashMap<String, AbstractCustomer> map = new HashMap<>() {
            {
                put("buyer", buyer);
                put("payer", buyer);
            }
        };
        Mediator.customerInstances.put(orderId, map);
        if (role.equals("B")) {
            buyer.messageTransfer(orderId, targetCustomer, payResult);
        } else if (role.equals("P")) {
            payer.messageTransfer(orderId, targetCustomer, payResult);
        }

    }

    public boolean changeStateAction(Message<OrderStateChangeAction> message, Order order) {
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
}
