package com.example.wz1.ec.core.utils;

import com.example.wz1.ec.core.app.ECApp;

/**
 * Created by wz on 2018/9/2.
 */

public class DimeUtils {
    public static float getWidthMetrics(){
        return ECApp.getApplication().getResources().getDisplayMetrics().widthPixels;
    }

    public static float getHeightMetrics(){
        return ECApp.getApplication().getResources().getDisplayMetrics().heightPixels;
    }
}
