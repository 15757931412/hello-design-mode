package cn.ppxytest.designmodewang.service;

import cn.ppxytest.designmodewang.items.composite.ProductComposite;
import cn.ppxytest.designmodewang.pojo.ProductItem;
import cn.ppxytest.designmodewang.repo.ProductItemRepository;
import cn.ppxytest.designmodewang.util.RedisCommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductItemService {

    @Autowired
    private RedisCommonProcessor redisCommonProcessor;
    @Autowired
    private ProductItemRepository productItemRepository;

    public ProductComposite fetchAllItems() {
        Object cacheItems = redisCommonProcessor.get("item");
        if (cacheItems != null) {
            return (ProductComposite) cacheItems;
        }
        List<ProductItem> fetchDbItems = productItemRepository.findAll();
        ProductComposite items = generateProductTree(fetchDbItems);
        if (items == null) {
            throw new UnsupportedOperationException("Product items should not be empty in DB!");
        }

        redisCommonProcessor.set("item", items);
        return items;
    }

    private ProductComposite generateProductTree(List<ProductItem> fetchDbItems) {
        List<ProductComposite> composites = fetchDbItems.stream().map(i -> ProductComposite.builder()
                .id(i.getId())
                .name(i.getName())
                .pid(i.getPid())
                .build()
        ).toList();
        Map<Integer, List<ProductComposite>> collect = composites.stream().collect(Collectors.groupingBy(ProductComposite::getPid));
        composites.stream().forEach(item->{
            List<ProductComposite> list = collect.get(item.getId());
            item.setChild(list==null? Collections.emptyList():);
        });
    }
}
