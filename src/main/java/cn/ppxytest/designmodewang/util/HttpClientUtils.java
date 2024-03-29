package cn.ppxytest.designmodewang.util;

import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;

import java.io.IOException;

public class HttpClientUtils {
    public static JSONObject execute(String url, HttpMethod httpMethod) {
        HttpRequestBase http = null;
        try {
            HttpClient client = HttpClients.createDefault();
            if (httpMethod == HttpMethod.GET) {
                http = new HttpGet(url);
            } else {
                http = new HttpPost(url);
            }
            HttpEntity entity = client.execute(http).getEntity();
            return JSONObject.parseObject(EntityUtils.toString(entity));


        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) { 
            throw new RuntimeException(e);
        } finally {
            http.releaseConnection();
        }
    }
}
