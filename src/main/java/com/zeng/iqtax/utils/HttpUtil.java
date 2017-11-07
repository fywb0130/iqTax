package com.zeng.iqtax.utils;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static Object get(String url, Map<String, String> params) throws IOException {
        if (null == url) {
            LOGGER.warn("empty url!");
            return null;
        }
        String urlStr = url;
        if (null != params && !params.isEmpty()) {
            String paramStr = "";
            for (Map.Entry<String, String> entry : params.entrySet()) {
                paramStr = paramStr + entry.getKey() + "=" + entry.getValue() + "&";
            }
            urlStr = urlStr + "?" + paramStr;
        }

        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(urlStr);
        httpGet.setHeader("accept", "*/*");
        httpGet.setHeader("connection", "Keep-Alive");
        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
        HttpResponse response = client.execute(httpGet);

        URL realUrl = new URL(urlStr);
        URLConnection connection = realUrl.openConnection();
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
        connection.connect();
        Object resp = connection.getContent();

        return resp;
    }
}
