package cn.ppxytest.designmodewang.service;

import cn.ppxytest.designmodewang.pojo.Order;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 12:25
 */
public interface OrderServiceInterface {
    Order createOrder(String productId);

    Order pay(String orderId);

    Order send(String orderId);

    Order receive(String orderId);

    String getPayUrl(String orderId, Float price, Integer payType);

    void friendPay(String sourceCustomer, String orderId, String targetCustomer, String payResult, String role);
}
