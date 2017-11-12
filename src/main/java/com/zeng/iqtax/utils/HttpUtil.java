package com.zeng.iqtax.utils;

import com.zeng.iqtax.bean.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    public static void main(String[] args) {
        get("http://localhost:9396/addJob", null, null, null, null, null);
    }

    public static Response get(String url, Map<String, Object> params, Map<String, String> headers, Map<String, String> cookies, String referer, String origin) {
        if (null == url) {
            LOGGER.warn("empty url!");
            throw new RuntimeException("Empty url");
        }

        Response response = new Response();
        BufferedReader reader = null;
        try {
            HttpURLConnection connection = constructUrlConn(url, params, headers, cookies, referer, origin);
            connection.connect();
            Map<String,List<String>> respHeaders = connection.getHeaderFields();
            if (200 != connection.getResponseCode()) {
                LOGGER.error("Http get: {} error: {}", url, respHeaders);
                throw new RuntimeException("Http get error");
            }

            String line = null;
            String respMsg = "";
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (null != (line = reader.readLine())) {
                respMsg += line;
            }

            response.setCookies(respHeaders.get("Set-Cookie"));
            response.setMultiHeaders(respHeaders);
            response.setResultStr(respMsg);
        } catch (Exception e) {
            LOGGER.error(String.format("Get request %s error, params: %s, headers: %s, cookies: %s, referer: %s, origin: %s", url, params, headers, cookies, referer, origin), e);
            throw new RuntimeException("Get request error");
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException e) {
                LOGGER.error("Close read stream error", e);
            }
        }

        return response;
    }

    public static Response post(String url, Map<String, Object> params, Object body, Map<String, String> headers, Map<String, String> cookies, String referer, String origin) {
        if (null == url) {
            LOGGER.warn("empty url!");
            throw new RuntimeException("Empty url");
        }

        Response response = new Response();
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            HttpURLConnection connection = constructUrlConn(url, params, headers, cookies, referer, origin);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            writer = new PrintWriter(connection.getOutputStream());
            writer.print(JsonUtil.toNumricJson(body));
            writer.flush();

            Map<String,List<String>> respHeaders = connection.getHeaderFields();
            if (200 != connection.getResponseCode()) {
                LOGGER.error("Http post: {} error: {}", url, respHeaders);
                throw new RuntimeException("Http post error");
            }

            String line = null;
            String respMsg = "";
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (null != (line = reader.readLine())) {
                respMsg += line;
            }

            response.setCookies(respHeaders.get("Set-Cookie"));
            response.setMultiHeaders(respHeaders);
            response.setResultStr(respMsg);
        } catch (Exception e) {
            LOGGER.error(String.format("Get request %s error, params: %s, headers: %s, cookies: %s, referer: %s, origin: %s", url, params, headers, cookies, referer, origin), e);
            throw new RuntimeException("Get request error");
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
                if (null != writer) {
                    writer.close();
                }
            } catch (IOException e) {
                LOGGER.error("Close read stream error", e);
            }
        }

        return response;
    }

    private static HttpURLConnection constructUrlConn(String url, Map<String, Object> params, Map<String, String> headers, Map<String, String> cookies, String referer, String origin) throws IOException {
        String fullUrl = url;
        if (null != params && !params.isEmpty()) {
            String paramStr = "";
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                paramStr = paramStr + entry.getKey() + "=" + entry.getValue() + "&";
            }
            fullUrl = fullUrl + "?" + paramStr;
        }
        URL urlObj = new URL(fullUrl);
        URLConnection connection = urlObj.openConnection();

        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
        if (null != headers) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (null != cookies) {
            String cookieVal = "";
            for (Map.Entry<String, String> entry : cookies.entrySet()) {
                cookieVal += entry.getKey() + "=" + entry.getValue() + ";";
            }
            connection.setRequestProperty("Cookie", cookieVal.substring(0, cookieVal.length() -1));
        }
        if (null != referer && !referer.isEmpty()) {
            connection.setRequestProperty("referer", referer);
        }
        if (null != origin && !origin.isEmpty()) {
            connection.setRequestProperty("origin", origin);
        }

        return (HttpURLConnection) connection;
    }
}
