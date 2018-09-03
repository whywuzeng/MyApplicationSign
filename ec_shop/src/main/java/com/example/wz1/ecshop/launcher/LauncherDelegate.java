package com.example.wz1.ecshop.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.core.launcher.BaseTimerTask;
import com.example.wz1.ec.core.launcher.ITimerTask;

import java.util.Timer;

/**
 * Created by wz on 2018/9/3.
 */

public class LauncherDelegate extends BaseDelegate implements ITimerTask {

    private Timer mTimer;
    private long count=3;

    @Override
    public Object setLayout() {
        return null;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        mTimer = new Timer();
        BaseTimerTask baseTimerTask = new BaseTimerTask(this);
        mTimer.schedule(baseTimerTask,0,1000);
    }

    //看看这个是什么线程
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                count--;
                if (count<=0) {
                    mTimer.cancel();
                    mTimer = null;
                }
            }
        });
    }
}
