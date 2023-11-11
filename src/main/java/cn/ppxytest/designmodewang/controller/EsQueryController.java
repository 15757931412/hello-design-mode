package cn.ppxytest.designmodewang.controller;

import cn.ppxytest.designmodewang.iterator.EsSqlQuery;
import cn.ppxytest.designmodewang.service.EsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:38
 */
@RestController
public class EsQueryController {

    @Autowired
    private EsQueryService esQueryService;

    @PostMapping("/queryEsBySql")
    public Object queryEsBySql(@RequestBody EsSqlQuery query) {
        return esQueryService.queryEsBySql(query);
    }

}
