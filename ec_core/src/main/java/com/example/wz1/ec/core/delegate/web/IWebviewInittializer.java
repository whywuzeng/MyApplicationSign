package com.example.wz1.ec.core.delegate.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by wz on 2018/10/2.
 */

public interface IWebviewInittializer {

    void initWebView(WebView webView);

    WebViewClient initWebViewClient();

    WebChromeClient  initWebViewChromeClient();
}
