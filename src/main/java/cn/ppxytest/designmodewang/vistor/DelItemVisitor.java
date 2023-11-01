package cn.ppxytest.designmodewang.vistor;

import org.springframework.beans.factory.annotation.Autowired;

import cn.ppxytest.designmodewang.items.composite.AbstractProductItem;
import cn.ppxytest.designmodewang.items.composite.ProductComposite;
import cn.ppxytest.designmodewang.util.RedisCommonProcessor;

public class DelItemVisitor implements ItemVistor<AbstractProductItem>{
    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    public AbstractProductItem vistor(AbstractProductItem productItem) {
        ProductComposite currentItem = (ProductComposite) redisCommonProcessor.get("items");
        ProductComposite delItem = (ProductComposite) productItem;
        if (delItem.getId()==currentItem.getId()) {
            throw new UnsupportedOperationException("根节点不能删除");
        }
        if (delItem.getPid()==currentItem.getId()) {
            currentItem.delProductChild(delItem);
            return currentItem;
        }
        delChild(delItem,currentItem);
        return currentItem;
    }

    private void delChild(ProductComposite productItem, ProductComposite currentItem) {
        for (AbstractProductItem abstractProductItem : currentItem.getChild()) {
            ProductComposite item = (ProductComposite) abstractProductItem;
            if (item.getId()==productItem.getPid()) {
                item.delProductChild(productItem);
                break;
            }else{
                delChild(productItem, item);
            }
        }
    }
    
}
