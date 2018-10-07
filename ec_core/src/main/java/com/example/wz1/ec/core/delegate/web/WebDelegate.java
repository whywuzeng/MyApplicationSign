package com.example.wz1.ec.core.delegate.web;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.core.delegate.web.route.RouteKey;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by wz on 2018/10/2.
 */

public abstract class WebDelegate extends ECAppDelegate {

    private boolean mIsWebViewAvailable;

    private ReferenceQueue<WebView> referenceQueue = new ReferenceQueue<>();

    private WebView mWebView;

    protected abstract IWebviewInittializer setWebViewInitializer();

    private String Url;

    private ECAppDelegate topDelegate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.Url= arguments.getString(RouteKey.URL.name(),null);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        else {
            //在子类初始化 调用接口 用weakreference 包
            final IWebviewInittializer iWebviewInittializer = setWebViewInitializer();
            if (iWebviewInittializer != null) {
                WeakReference weakReference = new WeakReference(new WebView(_mActivity), referenceQueue);
                mWebView = (WebView) weakReference.get();
                iWebviewInittializer.initWebView(mWebView);
                mWebView.setWebViewClient(iWebviewInittializer.initWebViewClient());
                mWebView.setWebChromeClient(iWebviewInittializer.initWebViewChromeClient());
                mWebView.addJavascriptInterface(EcAppWebInterface.create(this),"EcAppJS");
                mIsWebViewAvailable=true;
            }
            else {
                throw new NullPointerException("IWebviewInittializer is null ");
            }
        }
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    public void setTopDelegate(ECAppDelegate delegate){
        topDelegate=delegate;
    }

    public ECAppDelegate getTopDelegate(){
        if (topDelegate==null)
        {
            return this;
        }else {
            return topDelegate;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mWebView.onPause();
    }

    @Override
    public void onDestroyView() {
        mIsWebViewAvailable=false;
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWebView.onResume();
    }

    public WebView getWebView() {
        if (mWebView==null)
        {
            throw new NullPointerException("mWebView is null");
        }
        if (!mIsWebViewAvailable)
        {
            return null;
        }
        return mWebView;
    }

    public String getUrl(){
        if (Url ==null)
        {
            throw new NullPointerException("Url is null");
        }
        return Url;
    }
}
