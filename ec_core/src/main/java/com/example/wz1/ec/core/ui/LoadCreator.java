package com.example.wz1.ec.core.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by wz on 2018/9/2.
 */

public class LoadCreator {
    private final static String TAG="LoadCreator";

    private final static Map<String,Indicator> INDICATOR_MAP=new WeakHashMap<>();

    //反射取 indcator 框架

    //map 缓存 判断是否有值

    public static AVLoadingIndicatorView loadIndicate(Context context, String type){
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        Indicator indicator;
        if (INDICATOR_MAP.get(type)!=null)
        {
            indicator=INDICATOR_MAP.get(type);
        }else {
            GetIndicate getIndicate = new GetIndicate(type, avLoadingIndicatorView).invoke();
            if (getIndicate.is()) return avLoadingIndicatorView;
            indicator = getIndicate.getIndicator();
        }
        avLoadingIndicatorView.setIndicator(indicator);
        return avLoadingIndicatorView;
    }

    private static class GetIndicate {
        private boolean myResult;
        private String type;
        private AVLoadingIndicatorView avLoadingIndicatorView;
        private Indicator indicator;

        public GetIndicate(String type, AVLoadingIndicatorView avLoadingIndicatorView) {
            this.type = type;
            this.avLoadingIndicatorView = avLoadingIndicatorView;
        }

        boolean is() {
            return myResult;
        }

        public Indicator getIndicator() {
            return indicator;
        }

        public GetIndicate invoke() {
            //先判断name是否为空
            if (TextUtils.isEmpty(type))
            {
                myResult = true;
                return this;
            }
            StringBuilder stringBuilder;
            if (!type.contains(".")) {
                String name = avLoadingIndicatorView.getClass().getPackage().getName();
                 stringBuilder = new StringBuilder(name);
                stringBuilder.append(".indicators").append(".").append(type);
                //stringbuilder 来 ，this。class.getname
                //indicators
            }else {
                //只能传一个完整的
                stringBuilder=new StringBuilder(type);
            }
            try {
                Class<?> tClass = Class.forName(stringBuilder.toString());
                 indicator = (Indicator) tClass.newInstance();
                INDICATOR_MAP.put(type,indicator);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG,"Didn't find your class , check the name again !");
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            myResult = false;
            return this;
        }
    }
}
