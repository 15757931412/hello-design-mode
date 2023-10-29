package cn.ppxytest.designmodewang.items.composite;

public abstract class AbstractProductItem {
    protected void addProductItem(AbstractProductItem item){
        throw new UnsupportedOperationException("Not Support child add!");
    }

    protected void delProductChild(AbstractProductItem item) {
        throw new UnsupportedOperationException("Not Support child remove!");
    }


}
