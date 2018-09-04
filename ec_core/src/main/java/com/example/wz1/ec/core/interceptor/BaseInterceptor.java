package com.example.wz1.ec.core.interceptor;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by wz on 2018/9/2.
 */

public abstract class BaseInterceptor implements Interceptor {

    public LinkedHashMap<String, String> getRequsetUrl(Chain chain){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        Request request = chain.request();
        HttpUrl url = request.url();
        int size= url.querySize();
        for (int i=0;i<size;i++)
        {
            linkedHashMap.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return linkedHashMap;
    }

    public String getRequestUrl(Chain chain,String key)
    {
        return chain.request().url().queryParameter(key);
    }

    public LinkedHashMap<String, String> getRequsetBody(Chain chain){
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap<>();
        Request request = chain.request();
        FormBody body = (FormBody) request.body();
        int size = body.size();
        for (int i=0;i<size;i++)
        {
            linkedHashMap.put(body.name(i),body.value(i));
        }
        return linkedHashMap;
    }

    public String getRequsetBody(Chain chain,String key)
    {
        return getRequsetBody(chain).get(key);
    }
}
