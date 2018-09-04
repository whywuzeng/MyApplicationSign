package com.example.wz1.ecshop.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;

import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.core.launcher.BaseTimerTask;
import com.example.wz1.ec.core.launcher.ITimerTask;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;

/**
 * Created by wz on 2018/9/3.
 */

public class LauncherDelegate extends BaseDelegate implements ITimerTask {

    private Timer mTimer;
    private long count=TIMER_COUNT;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView tvLauncherTimer;
    private static final int TIMER_COUNT=3;

    @Override
    public Object setLayout() {
        return R.layout.launcher_delegate2;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

        initTimer();
    }

    private void initTimer() {
        count= TIMER_COUNT;
        mTimer = new Timer();
       final BaseTimerTask  baseTimerTask = new BaseTimerTask(this);
        mTimer.schedule(baseTimerTask,0,1000);
    }

    //看看这个是什么线程
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                count--;
                tvLauncherTimer.setText(MessageFormat.format("点击跳过{0}",count));
                if (count<=0) {
                    mTimer.cancel();
                    mTimer = null;
                    
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mTimer==null) {
            initTimer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTimer!=null)
        {
            mTimer.cancel();
            mTimer=null;
        }
    }
}
