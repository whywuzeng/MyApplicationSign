package com.example.wz1.ec.core.delegate.web.webview;

import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by wz on 2018/10/2.
 */

public class WebViewInitializer  {

    public void initWebView(WebView webView)
    {
        WebView.setWebContentsDebuggingEnabled(true);
        //横向
        webView.setHorizontalScrollBarEnabled(false);
        //纵向
         webView.setVerticalScrollBarEnabled(false);
        //允许截图
        webView.setDrawingCacheEnabled(true);
        //屏蔽长按事件
        webView.setLongClickable(false);
        //初始化Websetting
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        String ua = settings.getUserAgentString();
        //setUserAgen
        settings.setUserAgentString(ua+"Ecapp");
        //隐藏缩放控件
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);

        //禁止缩放
        settings.setSupportZoom(false);
        //文件权限
        settings.setAllowFileAccess(true);
        settings.setAllowFileAccessFromFileURLs(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setAllowContentAccess(true);
        //缓存相关
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
    }
}
