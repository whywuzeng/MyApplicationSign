package com.example.wz1.mysigninapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.example.wz1.ec.core.activites.ProxyActivity;
import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.shop.launcher.ILauncherFinish;
import com.example.wz1.ec.shop.launcher.LauncherFinishTag;
import com.example.wz1.ec.shop.main.EcBottomDelegate;
import com.example.wz1.ec.shop.sign.ISignInListener;
import com.example.wz1.ec.shop.sign.SignInDelegate;
import com.example.wz1.ec.shop.sign.SignInTag;
import com.example.wz1.mysigninapplication.delegate.HomeDelegate;

/**
 * Created by wz on 2018/9/1.
 */

public class Main2Activity extends ProxyActivity implements ISignInListener,ILauncherFinish {

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

        return new EcBottomDelegate();
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }

    @Override
    public void onSignInSuccess(SignInTag tag) {
        switch (tag)
        {
            case SIGN_IN:
            case SIGN_UP:
                getSupportDelegate().start(new HomeDelegate());
                break;
        }
    }

    @Override
    public void onLauncherFinish(LauncherFinishTag tag) {
        switch (tag)
        {
            case SIGNED:
                //
                getSupportDelegate().startWithPop(new HomeDelegate());
                break;
            case NO_SIGNUP:
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
                default:
                    break;
        }
    }
}
