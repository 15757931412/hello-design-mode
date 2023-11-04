package cn.ppxytest.designmodewang.dprecated.state;

import cn.ppxytest.designmodewang.dprecated.DeprecatedConstants;
import cn.ppxytest.designmodewang.dprecated.observer.DeprecatedAbstractObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class DeprecatedAbstractOrderState {

    protected final List<DeprecatedAbstractObserver> observersList = DeprecatedConstants.OBSERVER_LIST;

    public void addObserver(DeprecatedAbstractObserver observer) {
        this.observersList.add(observer);
    }

    public void removeObserver(DeprecatedAbstractObserver observer) {
        this.observersList.remove(observer);
    }

    public void notifyObserver(String orderId, String orderState) {
        for (DeprecatedAbstractObserver observer : this.observersList) {
            observer.orderStateHandle(orderId,orderState);
        }
    }
    protected final String ORDER_WAIT_PAY = "ORDER_WAIT_PAY";
    protected final String ORDER_WAIT_SEND = "ORDER_WAIT_SEND";
    protected final String ORDER_WAIT_PECEIVE = "ORDER_WAIT_PECEIVE";
    protected final String ORDER_FINISH = "ORDER_FINISH";

    protected DeprecatedOrder createOrder(String orderId, String productId) {
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder payOrder(String orderId){
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder sendOrder(String orderId){
        throw new UnsupportedOperationException();
    }

    protected DeprecatedOrder receiveOrder(String orderId){
        throw new UnsupportedOperationException();
    }

}
