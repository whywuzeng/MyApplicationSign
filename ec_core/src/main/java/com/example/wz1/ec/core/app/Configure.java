package com.example.wz1.ec.core.app;

import android.content.Context;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;

/**
 * Created by Administrator on 2018-08-31.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.app
 */

public class Configure {

    //存取各类单个配置
    private final static Map<Object,Object> CONFIGURE_KEY=new HashMap<>();

    //存取相同类型多个配置 如 iconfont iconfty类型
    private final static List<IconFontDescriptor> ICONFONT=new ArrayList<>();

    //测试修改  ds
    private final static Handler HANDLER=new Handler();

    //interceptor
    private static final ArrayList<Interceptor> INTERCEPTOR_MAP= new ArrayList<>();


    public Configure(){
        //准备工作 设置为false
        CONFIGURE_KEY.put(ConfigureKey.CONFIGURE_READY,false);
        //设置全局的handle
        CONFIGURE_KEY.put(ConfigureKey.CONFIGURE_HANDLE,HANDLER);
    }

    final Map<Object,Object> getConfigureKey()
    {
        return CONFIGURE_KEY;
    }

    public Configure withIcon(IconFontDescriptor mIcon)
    {
        ICONFONT.add(mIcon);
        return this;
    }

    public Configure configureAppContext(Context context)
    {
        CONFIGURE_KEY.put(ConfigureKey.CONFIGURE_APP_CONTEXT,context);
        return this;
    }

    public Configure configureApiHost(String host) {
        CONFIGURE_KEY.put(ConfigureKey.CONFIGURE_APIHOST, host);
        return this;
    }

    public Configure configureContext(Context context)
    {
        CONFIGURE_KEY.put(ConfigureKey.CONFIGURE_CONTEXT,context);
        return this;
    }

    public Configure withInterceptor(ArrayList<Interceptor> interceptors)
    {
        this.INTERCEPTOR_MAP.addAll(interceptors);
        return this;
    }

    public Configure withInterceptor(Interceptor interceptor)
    {
        this.INTERCEPTOR_MAP.add(interceptor);
        return this;
    }

    public ArrayList<Interceptor> getInterceptorMap(){
        return INTERCEPTOR_MAP;
    }

    private final void checkConfigure(ConfigureKey key)
    {
        boolean isReady = (boolean) CONFIGURE_KEY.get(ConfigureKey.CONFIGURE_READY);
        if (!isReady)
        {
            throw new RuntimeException("configuration is ready false ,call configure");
        }
    }

    final <T> T getConfigureValue(ConfigureKey key)
    {
       checkConfigure(key);
        Object value = CONFIGURE_KEY.get(key);
        if (value==null)
        {
            throw new RuntimeException("configure value is null key:"+key.toString());
        }
        return (T)value ;
    }

    // configure
//    1.Log框架可以配置adpter
//    2.准备为true
//  3 初始化 blankj工具类
    public void configure(){
        CONFIGURE_KEY.put(ConfigureKey.CONFIGURE_READY,true);
        initIconFont();
    }

    private void initIconFont(){

        Iconify.IconifyInitializer with = Iconify.with(ICONFONT.get(0));
        for (int i=1;i<ICONFONT.size();i++)
        {
            with.with(ICONFONT.get(i));
        }
    }

    //    单例模式
/////////////////////////////////////////////////////////////////////////////
    public static Configure getInstance() {
        return Holder.Instance;
    }

    private final static class Holder {
        private final static Configure Instance = new Configure();
    }
}
