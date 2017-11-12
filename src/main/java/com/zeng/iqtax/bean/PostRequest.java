package com.zeng.iqtax.bean;

import com.zeng.iqtax.utils.HttpUtil;

public class PostRequest extends Request {
    private Object body;

    @Override
    public Response sendRequest() {
        return HttpUtil.post(getUrl(), getParams(), body, getHeaders(), getCookies(), getReferer(), getOrigin());
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
