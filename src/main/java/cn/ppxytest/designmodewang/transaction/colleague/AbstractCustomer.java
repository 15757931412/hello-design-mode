package cn.ppxytest.designmodewang.transaction.colleague;

import cn.ppxytest.designmodewang.transaction.mediator.AbstractMediator;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 14:39
 */
public abstract class AbstractCustomer {
    public AbstractMediator mediator;
    public String orderId;
    public String customerName;

    AbstractCustomer(String orderId, AbstractMediator mediator, String customerName) {
        this.mediator = mediator;
        this.orderId = orderId;
        this.customerName = customerName;
    }
    public String getCustomerName(){
        return this.customerName;
    }

    public abstract void messageTransfer(String orderId, String targetCustomer, String payResult);
}
