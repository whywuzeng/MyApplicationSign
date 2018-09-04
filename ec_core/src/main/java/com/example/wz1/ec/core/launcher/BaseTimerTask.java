package com.example.wz1.ec.core.launcher;

import java.util.TimerTask;

/**
 * Created by wz on 2018/9/3.
 */

public class BaseTimerTask extends TimerTask{

    private ITimerTask iTimerTask;

    public BaseTimerTask(ITimerTask iTimerTask) {
        this.iTimerTask = iTimerTask;
    }

    @Override
    public void run() {
        if (iTimerTask!=null)
        {
            iTimerTask.onTimer();
        }
    }
}
