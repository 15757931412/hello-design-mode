package cn.ppxytest.designmodewang.pay.strategy.factory;

public abstract class AbstractPayContextFactory<T> {
    public abstract T getContext(Integer payType);
}
