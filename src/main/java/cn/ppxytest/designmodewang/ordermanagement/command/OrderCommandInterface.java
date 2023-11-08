package cn.ppxytest.designmodewang.ordermanagement.command;

import cn.ppxytest.designmodewang.pojo.Order;

public interface OrderCommandInterface {
    void execute(Order order);
}
