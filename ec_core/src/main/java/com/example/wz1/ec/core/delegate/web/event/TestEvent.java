package com.example.wz1.ec.core.delegate.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wz1.ec.core.delegate.web.WebDelegate;

/**
 * Created by wz on 2018/10/3.
 */

public class TestEvent extends Event {
    public TestEvent(Context mContext, WebDelegate mDelegate, String URL, String mAction) {
        super(mContext, mDelegate, URL, mAction);
    }

    @Override
    public void execute() {
        ToastUtils.showShort(getmAction());
        if(getmAction().equals("test"))
        {
            WebView webView = getmWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    getmWebView().evaluateJavascript("nativeCall();",null);
                }
            });
        }

    }
}
