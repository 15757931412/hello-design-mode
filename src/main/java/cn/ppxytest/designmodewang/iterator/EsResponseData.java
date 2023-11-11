package cn.ppxytest.designmodewang.iterator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsResponseData {
    private List<Map<String,String>> columns;
    private List<List<Object>> rows;
    private String cursor;
}
