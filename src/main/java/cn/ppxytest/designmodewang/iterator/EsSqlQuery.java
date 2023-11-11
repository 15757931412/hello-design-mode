package cn.ppxytest.designmodewang.iterator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:25
 */
@Data
@JsonIgnoreProperties
public class EsSqlQuery implements EsSqlQueryInterface<EsQueryIterator>{
    private String query;
    private Long fetchSize;
    private String cursor;
    public EsSqlQuery(String cursor){
        this.cursor = cursor;
    }

    public EsSqlQuery(String query, Long fetchSize) {
        this.query = query;
        this.fetchSize = fetchSize;
    }
    @Override
    public EsQueryIterator iterator() {
        return new EsQueryIterator(this.query,this.fetchSize);
    }
}
