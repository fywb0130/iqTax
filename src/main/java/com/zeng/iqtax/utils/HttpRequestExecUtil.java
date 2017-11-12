//package com.zeng.iqtax.utils;
//
//import com.zeng.iqtax.bean.GetRequest;
//import com.zeng.iqtax.bean.PostRequest;
//import com.zeng.iqtax.bean.Response;
//
//public class HttpRequestExecUtil {
//    public static Response get(GetRequest request) {
//        return HttpUtil.get(request.getUrl(), request.getParams(), request.getHeaders(), request.getCookies(), request.getReferer(), request.getOrigin());
//    }
//
//    public static Response post(PostRequest request) {
//        return HttpUtil.post(request.getUrl(), request.getParams(), request.getBody(), request.getHeaders(), request.getCookies(), request.getReferer(), request.getOrigin());
//    }
//}
