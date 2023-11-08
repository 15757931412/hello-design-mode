package cn.ppxytest.designmodewang.ordermanagement.command;

import cn.ppxytest.designmodewang.ordermanagement.command.receiver.OrderCommandReceiver;
import cn.ppxytest.designmodewang.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderCommand implements OrderCommandInterface{
    @Autowired
    private OrderCommandReceiver orderCommandReceiver;

    @Override
    public void execute(Order order) {
        this.orderCommandReceiver.action(order);

    }
}
