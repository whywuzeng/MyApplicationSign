package com.example.wz1.ec.core.delegate.web.webclient;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wz1.ec.core.delegate.web.WebDelegate;

/**
 * Created by wz on 2018/10/2.
 */

public class WebDelegateClientImpl extends WebViewClient {

    private final WebDelegate mWebDelegate;

    public WebDelegateClientImpl(WebDelegate mWebDelegate) {
        this.mWebDelegate = mWebDelegate;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        return Router.getInstanse().handleUrl(mWebDelegate, url);
    }

}
