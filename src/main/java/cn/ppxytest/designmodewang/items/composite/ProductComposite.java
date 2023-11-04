package cn.ppxytest.designmodewang.items.composite;

import cn.ppxytest.designmodewang.pojo.ProductItem;
import lombok.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductComposite extends AbstractProductItem {
    private int id;
    private int pid;
    private String name;
    private List<AbstractProductItem> child = new ArrayList<>();

    public void addProductItem(AbstractProductItem item) {
        this.child.add(item);
    }

    public void delProductChild(AbstractProductItem item) {
        ProductComposite removeItem = (ProductComposite) item;
        Iterator<AbstractProductItem> iterator = child.iterator();
        while (iterator.hasNext()) {
            ProductComposite composite = (ProductComposite) iterator.next();
            if (composite.getId() == removeItem.getId()) {
                iterator.remove();
                break;
            }
        }
    }
}
