package com.zeng.iqtax.controller;

import com.zeng.iqtax.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class Entrance {
    private static final Logger LOGGER = LoggerFactory.getLogger(Entrance.class);

    @RequestMapping("addJob")
    public Object addJob() throws IOException {
        return HttpUtil.get("http://www.cnblogs.com/zhuawang/archive/2012/12/08/2809380.html", null);
    }
}
