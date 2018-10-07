package com.example.wz1.ec.core.delegate.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.example.wz1.ec.core.delegate.web.WebDelegate;

/**
 * Created by wz on 2018/10/3.
 */

public abstract class Event implements IEvent {
    private Context mContext;
    private WebDelegate mDelegate;
    private String URL;
    private String mAction;

    public WebView getmWebView() {
        return mDelegate.getWebView();
    }

    private WebView mWebView;

    public Event(Context mContext, WebDelegate mDelegate, String URL, String mAction) {
        this.mContext = mContext;
        this.mDelegate = mDelegate;
        this.URL = URL;
        this.mAction = mAction;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public WebDelegate getmDelegate() {
        return mDelegate;
    }

    public void setmDelegate(WebDelegate mDelegate) {
        this.mDelegate = mDelegate;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getmAction() {
        return mAction;
    }

    public void setmAction(String mAction) {
        this.mAction = mAction;
    }
}
