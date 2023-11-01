package cn.ppxytest.designmodewang.vistor;

import cn.ppxytest.designmodewang.items.composite.AbstractProductItem;

public interface ItemVistor<T> {
    T vistor(AbstractProductItem productItem);
}
