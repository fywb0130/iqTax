package com.zeng.iqtax.bean;

import com.zeng.iqtax.utils.JsonUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {
    private String resultStr;
    private Map<String, Object> result;
    private Map<String, String> cookies;
    private Map<String, String> headers;

    public void parseJsonResult() {
        result = JsonUtil.fromNumricJson(resultStr, Map.class);
    }

    public void parseHtmlResult() {
        Document document = Jsoup.parse(resultStr);

    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(List<String> cookies) {
        if (null == cookies || cookies.isEmpty()) {
            return;
        }
        if (null == this.cookies) {
            this.cookies = new HashMap<>();
        }
        for (String cookie : cookies) {
            String[] ss = cookie.split("=");
            this.cookies.put(ss[0], ss[1]);
        }
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setMultiHeaders(Map<String,List<String>> headers) {
        if (null == headers || headers.isEmpty()) {
            return;
        }
        if (null == this.headers) {
            this.headers = new HashMap<>();
        }
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            if (isValuableHeader(entry)) {
                this.headers.put(entry.getKey(), entry.getValue().get(0));
            }
        }
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    private boolean isValuableHeader(Map.Entry<String, List<String>> entry) {
        if (null == entry.getKey()) {
            return false;
        }
        if ("Set-Cookie".equals(entry.getKey())) {
            return false;
        }
        if ("Content-Type".equals(entry.getKey())) {
            return false;
        }
        if ("Content-Length".equals(entry.getKey())) {
            return false;
        }
        if ("Date".equals(entry.getKey())) {
            return false;
        }
        return true;
    }
}
