package com.example.wz1.ec.core.interceptor;

import com.example.wz1.ec.core.utils.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wz on 2018/9/2.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int RAW_RES;

    public DebugInterceptor(String DEBUG_URL, int RAW_RES) {
        this.DEBUG_URL = DEBUG_URL;
        this.RAW_RES = RAW_RES;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if (chain.request().url().url().toString().contains(DEBUG_URL))
        {
          return getResponseBody(chain,RAW_RES);
        }
        return chain.proceed(chain.request());
    }

    public Response getResponseBody(String json,Chain chain){
        return new Response.Builder().code(200)
                .addHeader("Content-type","application/json")
                .body(ResponseBody.create(MediaType.parse("application/json;charset=utf-8"),json))
                        .message("ok")
                .protocol(Protocol.HTTP_1_1)
                .request(chain.request())
                .build();

    }

    public Response getResponseBody(Chain chain,int id){
        String rawFile = FileUtil.getRawFile(id);
        return getResponseBody(rawFile,chain);
    }

}
