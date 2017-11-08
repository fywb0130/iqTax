package com.zeng.iqtax.controller;

import com.zeng.iqtax.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Entrance {
    private static final Logger LOGGER = LoggerFactory.getLogger(Entrance.class);

    @RequestMapping("addJob")
    public Object addJob() throws IOException {
        return HttpUtil.get("http://192.168.83.107:8080/demoPath/get?a=23", null, null, null);
    }

    @RequestMapping("get")
    public Object get() {
        Map<String, Object> params = new HashMap<>();
        params.put("a", 2);
        Map<String, String> header = new HashMap<>();
        header.put("token", "23");
        Map<String, String> cookies = new HashMap<>();
        cookies.put("name", "zeng");
        return HttpUtil.get("http://192.168.83.107:8080/demoPath/get", params, header, cookies);
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
        return HttpUtil.post("http://192.168.83.107:8080/demoPath/post", null, body, header, cookies);
    }
}
