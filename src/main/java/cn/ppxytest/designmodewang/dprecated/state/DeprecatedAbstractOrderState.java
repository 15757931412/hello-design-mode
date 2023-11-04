package cn.ppxytest.designmodewang.dprecated.state;

public abstract class DeprecatedAbstractOrderState {
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
