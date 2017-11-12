package com.zeng.iqtax.service;

import com.zeng.iqtax.bean.PostRequest;
import com.zeng.iqtax.bean.Request;
import com.zeng.iqtax.bean.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Engine {
    private static final Logger LOGGER = LoggerFactory.getLogger(Engine.class);

    private static final Date date = new Date();

    private static final int times = 3;

    @Scheduled(cron = "* * * * * ?")
    public void runJob() {
        long now = System.currentTimeMillis();
        if (now < date.getTime()) {
            return;
        }
        for (int i = 0; i < times; i++) {
            doJob();
        }
    }

    public void doJob() {
        Request now, next;

        Response response = now.sendRequest();
        next = new;
        next.addCookie(response.getCookies());
        next.addHeaders(response.getHeaders());

        now = next;
        response = now.sendRequest();

        next = new;
        next.addCookie(response.getCookies());
        next.addHeaders(response.getHeaders());

    }

}
