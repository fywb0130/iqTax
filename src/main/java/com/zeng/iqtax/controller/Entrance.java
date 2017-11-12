package com.zeng.iqtax.controller;

import com.zeng.iqtax.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Entrance {
    private static final Logger LOGGER = LoggerFactory.getLogger(Entrance.class);

    @RequestMapping(value = "addJob", method = RequestMethod.GET)
    public Object addJob(HttpServletResponse response) throws IOException {
        Cookie cookie1 = new Cookie("zeng", "chan");
        Cookie cookie2 = new Cookie("age", "93");
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        response.addHeader("h1", "v1");
        return "OK";
    }

    @RequestMapping("get")
    public Object get() {
        Map<String, Object> params = new HashMap<>();
        params.put("a", 2);
        Map<String, String> header = new HashMap<>();
        header.put("token", "23");
        Map<String, String> cookies = new HashMap<>();
        cookies.put("name", "zeng");
        return HttpUtil.get("http://192.168.83.107:8080/demoPath/get", params, header, cookies, "http://192.168.83.107:8080/demoPath/post", "http://192.168.83.107:8080");
    }

    @RequestMapping("post")
    public Object post() {
        Map<String, Object> params = new HashMap<>();
        params.put("a", 2);
        Map<String, String> header = new HashMap<>();
        header.put("token", "23");
        Map<String, String> cookies = new HashMap<>();
        cookies.put("name", "zeng");
        Map<String, Object> body = new HashMap<>();
        body.put("params", params);
        body.put("headers", header);
        body.put("cookies", cookies);
        return HttpUtil.post("http://192.168.83.107:8080/demoPath/post", null, body, header, cookies, "http://192.168.83.107:8080/demoPath/get", "http://192.168.83.107:8080");
    }
}
