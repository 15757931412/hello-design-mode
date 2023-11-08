package cn.ppxytest.designmodewang.ordermanagement.command.invoker;

import cn.ppxytest.designmodewang.ordermanagement.command.OrderCommandInterface;
import cn.ppxytest.designmodewang.pojo.Order;

public class OrderCommandInvoker {
    public void invoke(OrderCommandInterface command, Order order) {
        command.execute(order);
    }
}
