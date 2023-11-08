package cn.ppxytest.designmodewang.pay.strategy;

import cn.ppxytest.designmodewang.pojo.Order;

public interface PayStrategyInterface {
    String pay(Order order);
}
