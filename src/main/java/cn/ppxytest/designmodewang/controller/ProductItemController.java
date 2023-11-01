package cn.ppxytest.designmodewang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.ppxytest.designmodewang.items.composite.ProductComposite;
import cn.ppxytest.designmodewang.pojo.ProductItem;
import cn.ppxytest.designmodewang.service.ProductItemService;

@RestController
@RequestMapping("/product")
public class ProductItemController {

    @Autowired
    private ProductItemService productItemService;
    
    @PostMapping("/addItems")
    public ProductComposite addItems(@RequestBody ProductItem item){   
        return productItemService.addItem(item);
    }

    @PostMapping("/delItems")
    public ProductComposite delItem(@RequestBody ProductItem item){
        return productItemService.delItems(item);
    }
}
