package com.example.wz1.mysigninapplication;

import android.app.Application;

import com.example.wz1.ec.core.app.ECApp;
import com.example.wz1.mysigninapplication.font.IconTianModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;

/**
 * Created by Administrator on 2018-08-30.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication
 */

public class SignInApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ECApp.init(this).withIcon(new FontAwesomeModule())
                .withIcon(new IconTianModule())
                .withIcon(new IoniconsModule())
                .configure();

        //一个configure class 配置类 集成各种 配置信息

        //appec class 类  初始化


    }
}
