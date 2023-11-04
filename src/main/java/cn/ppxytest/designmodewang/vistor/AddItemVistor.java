package cn.ppxytest.designmodewang.vistor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.ppxytest.designmodewang.items.composite.AbstractProductItem;
import cn.ppxytest.designmodewang.items.composite.ProductComposite;
import cn.ppxytest.designmodewang.util.RedisCommonProcessor;

@Component
public class AddItemVistor implements ItemVistor<AbstractProductItem>{

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;

    @Override
    public AbstractProductItem vistor(AbstractProductItem productItem) {
        ProductComposite currentItem = (ProductComposite) redisCommonProcessor.get("item");
        ProductComposite addItem = (ProductComposite)productItem;

        // 如果是父节点那么直接添加，不是父节点递归
        if(addItem.getPid()==currentItem.getId()){
            currentItem.addProductItem(addItem);
            return currentItem;
        }
        addChiled(addItem,currentItem);
        return currentItem;
    }

    private void addChiled(ProductComposite addItem, ProductComposite currentItem) {
        for (AbstractProductItem child : currentItem.getChild()) {
            ProductComposite item = (ProductComposite) child;
            if(item.getId()==addItem.getPid()){
                item.addProductItem(addItem);
                break;
            }else{
                addChiled(addItem, item);
            }            
        }
    }

    
}
