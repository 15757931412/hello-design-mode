package cn.ppxytest.designmodewang.transaction.mediator;

import cn.ppxytest.designmodewang.transaction.colleague.AbstractCustomer;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 14:37
 */
public abstract class AbstractMediator {
    public abstract void messageTransfer(String orderId, String targetCustomer, AbstractCustomer customer, String payResult);
}
