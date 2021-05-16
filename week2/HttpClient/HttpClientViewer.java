package com.geekbang.Week02_NIO;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


/**
 * 访问地址，获取返回信息
 */
public class HttpClientViewer {
    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8801";
        HttpClientViewer viewer = new HttpClientViewer();
        System.out.println(viewer.GetUrlResponse(url));
    }

    public String GetUrlResponse(String url) {
        String responseText = "";
        try{
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(RequestConfig.custom().setConnectTimeout(30000).build());
            CloseableHttpResponse response = client.execute(httpGet);
            System.out.println("response status code:" + response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseText = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseText;
    }
}
