package com.zeng.iqtax.bean;

import com.zeng.iqtax.utils.HttpUtil;

public class GetRequest extends Request {
    @Override
    public Response sendRequest() {
        return HttpUtil.get(getUrl(), getParams(), getHeaders(), getCookies(), getReferer(), getOrigin());
    }
}
