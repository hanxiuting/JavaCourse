package com.geekbang.week03.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        //添加新的响应头
        response.headers().set("javaResKey", "hxt");
    }
}
