package com.example.wz1.mysigninapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.example.wz1.ec.core.activites.ProxyActivity;
import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.shop.sign.SignUpDelegate;

/**
 * Created by wz on 2018/9/1.
 */

public class Main2Activity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null)
        {
            actionBar.hide();
        }

    }

    @Override
    public BaseDelegate setRootDelegate() {

        return new SignUpDelegate();
    }
}
