package com.example.wz1.ec.core.delegate.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wz1.ec.core.delegate.web.route.RouteKey;
import com.example.wz1.ec.core.delegate.web.webchromeclient.WebChromeClientImpl;
import com.example.wz1.ec.core.delegate.web.webclient.Router;
import com.example.wz1.ec.core.delegate.web.webclient.WebDelegateClientImpl;
import com.example.wz1.ec.core.delegate.web.webview.WebViewInitializer;

/**
 * Created by wz on 2018/10/2.
 */

public class WebDelegateImpl extends WebDelegate implements IWebviewInittializer{

    public static WebDelegateImpl getNewIntance(String url){
        Bundle bundle = new Bundle();
        bundle.putString(RouteKey.URL.name(),url);
        WebDelegateImpl webDelegate = new WebDelegateImpl();
        webDelegate.setArguments(bundle);
        return webDelegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(getUrl()))
        {
            //处理URL
            Router.getInstanse().loadUrl(this, getUrl());
        }
    }

    @Override
    protected IWebviewInittializer setWebViewInitializer() {
        return this;
    }

    @Override
    public void initWebView(WebView webView) {
        new WebViewInitializer().initWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        return new WebDelegateClientImpl(this);
    }

    @Override
    public WebChromeClient initWebViewChromeClient() {
        return new WebChromeClientImpl();
    }
}
