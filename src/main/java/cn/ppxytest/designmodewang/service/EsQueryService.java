package cn.ppxytest.designmodewang.service;

import cn.ppxytest.designmodewang.iterator.EsQueryIterator;
import cn.ppxytest.designmodewang.iterator.EsSqlQuery;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:38
 */
@Service
public class EsQueryService {
    public Object queryEsBySql(EsSqlQuery query) {
        EsQueryIterator iterator = query.iterator();
        Stream<Map<String, Object>> stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false);
        return stream.toList();
    }
}
