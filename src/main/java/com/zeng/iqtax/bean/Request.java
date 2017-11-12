package com.zeng.iqtax.bean;

import java.util.Map;

public abstract class Request {
    private String url;
    private Map<String, Object> params;
    private Map<String, String> headers;
    private Map<String, String> cookies;
    private String referer;
    private String origin;

    protected Request next;

    public abstract Response  sendRequest();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public void addParams(Map<String, Object> params) {
        if (null == params) {
            this.params = params;
        } else {
            this.params.putAll(params);
        }
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeaders(Map<String, String> headers) {
        if (null == this.headers) {
            this.headers = headers;
        } else {
            this.headers.putAll(headers);
        }
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public void setCookies(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    public void addCookie(Map<String, String> cookies) {
        if (null == this.cookies) {
            this.cookies = cookies;
        } else {
            this.cookies.putAll(cookies);
        }
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Request getNext() {
        return next;
    }

    public void setNext(Request next) {
        this.next = next;
    }
}
