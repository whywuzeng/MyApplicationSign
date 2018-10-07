package com.example.wz1.ec.core.delegate.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wz1.ec.core.delegate.web.event.EventManager;
import com.example.wz1.ec.core.delegate.web.event.TestEvent;

/**
 * Created by wz on 2018/10/2.
 */

public final class EcAppWebInterface  {
    private final WebDelegate DELEGATE;

    public EcAppWebInterface(WebDelegate delegate) {
        DELEGATE = delegate;
    }

    public static EcAppWebInterface create(WebDelegate delegate)
    {
        return new EcAppWebInterface(delegate);
    }

    @JavascriptInterface
    public String event(String params){
        JSONObject object = JSON.parseObject(params);
        String action = object.getString("action");
        TestEvent testEvent = new TestEvent(DELEGATE.getContext(), DELEGATE, DELEGATE.getUrl(), action);
        EventManager.getInstance().addEvent(action,testEvent);
        testEvent.execute();
        return null;
    }
}
