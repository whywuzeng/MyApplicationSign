package com.example.wz1.ec.core.app;

import android.app.Application;
import android.content.Context;

import java.util.Map;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2018-08-31.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.app
 */

public class ECApp {

    public static Configure init(Context context){
        return Configure.getInstance().configureAppContext(context).configureContext(context);
    }

    public static Map<Object,Object> getConfigureMap()
    {
        return Configure.getInstance().getConfigureKey();
    }

    public static Handler getConfigureHandler()
    {
        return Configure.getInstance().getConfigureValue(ConfigureKey.CONFIGURE_HANDLE);
    }

    public static Context getApplicationContext()
    {
        return Configure.getInstance().getConfigureValue(ConfigureKey.CONFIGURE_APP_CONTEXT);
    }

    public static Application getApplication()
    {
        return Configure.getInstance().getConfigureValue(ConfigureKey.CONFIGURE_CONTEXT);
    }
}
