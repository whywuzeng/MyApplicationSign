package com.example.wz1.ec.core.delegate.web.webclient;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.core.delegate.web.WebDelegate;
import com.example.wz1.ec.core.delegate.web.WebDelegateImpl;

/**
 * Created by wz on 2018/10/2.
 */

public class Router {

    private static final class HOLDER {
        public static final Router mIntanse=new Router();
    }

    private Router(){}

    public static Router getInstanse(){
        return HOLDER.mIntanse;
    }

    public boolean handleUrl(WebDelegate delegate, String url)
    {
        WebDelegateImpl newDelegate = WebDelegateImpl.getNewIntance(url);
        //处理URl
        if (url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }
        ECAppDelegate topDelegate = delegate.getTopDelegate();
        topDelegate.start(newDelegate);
//        BaseDelegate parentDelegate = delegate.getParentDelegate();
//        if (parentDelegate!=null)
//        {
//            parentDelegate.start(newDelegate);
//        }else {
//            delegate.start(newDelegate);
//        }

        return true;
    }

    private void callPhone(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri parse = Uri.parse(url);
        intent.setData(parse);
        context.startActivity(intent);
    }

    private void loadPage(WebView webView, String url) {
        if (!TextUtils.isEmpty(url))
        {
            webView.loadUrl(url);
        }else {
            throw new NullPointerException("loadPage url is null");
        }
    }

    private void loadLoaclPage(WebView webView,String url)
    {
        String localUrl="file:///android_asset/"+url;
        loadPage(webView,localUrl);
    }

    public void loadUrl(WebView webView,String url)
    {
        if (URLUtil.isNetworkUrl(url)||URLUtil.isAssetUrl(url))//url不是网络的 也不是本地的 。就只能拼接成本地url
        {
            loadPage(webView,url);
        }else {
            loadLoaclPage(webView,url);
        }
    }

    public void loadUrl(WebDelegate delegate,String url)
    {
        loadUrl(delegate.getWebView(),url);
    }
}
