package cn.ppxytest.designmodewang.service.decorator;

import cn.ppxytest.designmodewang.pojo.Order;
import cn.ppxytest.designmodewang.service.OrderServiceInterface;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 12:28
 */
public abstract class AbstractOrderServiceDecorator implements OrderServiceInterface {
    private OrderServiceInterface orderServiceInterface;

    @Override
    public Order createOrder(String productId) {
        return this.createOrder(productId);
    }

    @Override
    public Order pay(String orderId) {
        return this.orderServiceInterface.pay(orderId);
    }

    @Override
    public Order send(String orderId) {
        return this.orderServiceInterface.send(orderId);
    }

    @Override
    public Order receive(String orderId) {
        return this.orderServiceInterface.receive(orderId);
    }

    @Override
    public String getPayUrl(String orderId, Float price, Integer payType) {
        return this.orderServiceInterface.getPayUrl(orderId, price, payType);
    }

    protected abstract void updateScoreAndSendRedPaper(String productId, int serviceLevel, float price);

    public void setOrderServiceInterface(OrderServiceInterface orderServiceInterface) {
        this.orderServiceInterface = orderServiceInterface;
    }
}
