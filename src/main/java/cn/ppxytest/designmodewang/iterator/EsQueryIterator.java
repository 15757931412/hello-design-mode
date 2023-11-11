package cn.ppxytest.designmodewang.iterator;

import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:25
 */
public class EsQueryIterator implements Iterator<Map<String, Object>> {
    Iterator<Map<String, Object>> iterator;
    RestTemplate restTemplate = new RestTemplate();
    private String cursor;
    private List<String> columns;

    public EsQueryIterator(String query, Long fetchSize) {
        EsResponseData esResponseData = restTemplate.postForObject("http://localhost:9200/_sql?format=json", new EsSqlQuery(query, fetchSize), EsResponseData.class);
        this.cursor = esResponseData.getCursor();
        this.columns = esResponseData.getColumns().stream().map(x -> x.get("name")).toList();
        this.iterator = convert(columns, esResponseData).iterator();
    }

    private List<Map<String, Object>> convert(List<String> columns, EsResponseData esResponseData) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (List<Object> row : esResponseData.getRows()) {
            HashMap<String, Object> map = new HashMap<>();
            for (int i = 0; i < columns.size(); i++) {
                map.put(columns.get(i), row.get(i));
            }
            result.add(map);
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext() || scrollNext();
    }

    private boolean scrollNext() {
        if (iterator == null || this.cursor == null) {
            return false;
        }
        EsResponseData esResponseData = restTemplate.postForObject("http://localhost:9200/_sql?format=json", new EsSqlQuery(this.cursor), EsResponseData.class);
        this.cursor = esResponseData.getCursor();
        this.columns = esResponseData.getColumns().stream().map(x -> x.get("name")).toList();
        this.iterator = convert(columns, esResponseData).iterator();
        return iterator.hasNext();
    }

    @Override
    public Map<String, Object> next() {
        return iterator.next();
    }
}
