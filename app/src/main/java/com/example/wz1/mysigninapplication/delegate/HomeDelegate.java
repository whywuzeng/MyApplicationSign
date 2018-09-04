package com.example.wz1.mysigninapplication.delegate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.wz1.ec.core.delegate.CheckDelegate;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.IRequest;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.mysigninapplication.R;

/**
 * Created by wz on 2018/9/1.
 */

public class HomeDelegate extends CheckDelegate {

    private final static String TAG="HomeDelegate";

    @Override
    public Object setLayout() {

        return R.layout.home_delegate;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

        RestClient build = new RestClient.RestClientBuild().url("http://127.0.0.1/")
                .params("", "")
                .context(_mActivity)
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        Log.e(TAG, "onRequestStart:");
                    }

                    @Override
                    public void onRequestComplete() {
                        Log.e(TAG, "onRequestComplete: ");
                    }
                }).error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Log.e(TAG, "onError: " + message);
                    }
                }).failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                }).sucess(new ISucess() {
                    @Override
                    public void onSucess(String result) {
                        Toast.makeText(_mActivity, result, Toast.LENGTH_SHORT).show();
                    }
                }).build();
        build.get();
    }
}
