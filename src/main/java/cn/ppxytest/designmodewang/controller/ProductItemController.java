package cn.ppxytest.designmodewang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.ppxytest.designmodewang.items.composite.ProductComposite;
import cn.ppxytest.designmodewang.pojo.ProductItem;
import cn.ppxytest.designmodewang.service.ProductItemService;

@RestController
@RequestMapping("/product")
public class ProductItemController {

    @Autowired
    private ProductItemService productItemService;

    @PostMapping("/addItems")
    public ProductComposite addItems(@RequestBody ProductItem item) {
        return productItemService.addItem(item);
    }

    @PostMapping("/delItems")
    public ProductComposite delItem(@RequestBody ProductItem item) {
        return productItemService.delItems(item);
    }

    @GetMapping("/all")
    public ProductComposite fetchAll() {
        return productItemService.fetchAllItems();
    }
}
