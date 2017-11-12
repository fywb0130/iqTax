package com.zeng.iqtax.bean;

import java.util.LinkedList;
import java.util.List;

public class RequestChain {
    private List<Request> requestList;
    private Request last;

    public RequestChain() {
        requestList = new LinkedList<>();
    }

    public void addRequest(Request request) {
        if (null != last) {
            request.setHeaders(last.getHeaders());
            request.setCookies(last.getCookies());
            request.setReferer(last.getUrl());
            request.setOrigin(last.getOrigin());
            last.setNext(request);
        }
        requestList.add(request);
        last = request;
    }

    public Response ExecRequestChain() {
        return null;
    }
}
