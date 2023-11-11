package cn.ppxytest.designmodewang.transaction.colleague;

import cn.ppxytest.designmodewang.transaction.mediator.AbstractMediator;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 14:42
 */
public class Buyer extends AbstractCustomer {
    public Buyer(String orderId, AbstractMediator mediator, String customerName) {
        super(orderId, mediator, customerName);
    }

    @Override
    public void messageTransfer(String orderId, String targetCustomer, String payResult) {
        super.mediator.messageTransfer(orderId, targetCustomer, this, payResult);
    }
}
