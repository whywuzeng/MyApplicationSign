package com.example.wz1.ec.core.net;

import com.example.wz1.ec.core.app.Configure;
import com.example.wz1.ec.core.app.ConfigureKey;
import com.example.wz1.ec.core.app.ECApp;

import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by wz on 2018/9/1.
 */

public class RestCreator {

    public static final Map<String,Object> params=new WeakHashMap<>();

    public static RestService getRestservice() {
        return ServiceHolder.RESTSERVICE;
    }
    // service  实例
     public static final class ServiceHolder{

        private static final RestService RESTSERVICE = RetrofitHolder.RETROFIT.create(RestService.class);
     }
    //okhttp 实例

    public static final class OkhttpHolder{
        private static final int TIME_OUT=60;
        private static final ArrayList<Interceptor> INTERCEPTOR_MAP= Configure.getInstance().getInterceptorMap();
        private static final OkHttpClient.Builder BUILDER=new OkHttpClient.Builder();
        private static  OkHttpClient.Builder addInterceptor(){
            if (INTERCEPTOR_MAP!=null&&!INTERCEPTOR_MAP.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTOR_MAP) {
                    BUILDER.addInterceptor(interceptor);
                }
                return BUILDER;
            }
            return BUILDER;
        }
        private static final OkHttpClient OK_HTTP_CLIENT=addInterceptor().connectTimeout(TIME_OUT, TimeUnit.SECONDS).build();
    }

    //retrofit 实例
    public static final class RetrofitHolder{
        private static final Retrofit RETROFIT=new Retrofit.Builder().baseUrl((String) ECApp.getConfigureMap().get(ConfigureKey.CONFIGURE_APIHOST)).client(OkhttpHolder.OK_HTTP_CLIENT).addConverterFactory(ScalarsConverterFactory.create()).build();

        public static Retrofit getRETROFIT() {
            return RETROFIT;
        }
    }
}
