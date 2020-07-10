package com.myself.supermarket.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class WechatClientUtil {
    public static String get(String uri){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(uri);
        CloseableHttpResponse resp = null;
        try {
            resp = httpClient.execute(get);
            int statusCode = resp.getStatusLine().getStatusCode();
            if(statusCode==200){
                HttpEntity entity = resp.getEntity();
                String result = EntityUtils.toString(entity,"UTF-8");
                return result;
            }else{
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                resp.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }
}
