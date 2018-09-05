package com.example.wz1.ec.core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.ViewGroup;
import android.view.Window;

import com.example.wz1.ec.core.R;
import com.example.wz1.ec.core.utils.DimeUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wz on 2018/9/2.
 */

public class ECAppLoader {
    //dialog的样式

    private static final int METRICS_SCALE=8;

    private static final List<AppCompatDialog> APP_COMPAT_DIALOGS=new ArrayList<>();

    //list统一管理
    public static void showLoading(Context context,LoadStyle style){
        AppCompatDialog appCompatDialog = new AppCompatDialog(context, R.style.dialog);
        Window window = appCompatDialog.getWindow();
        float widthMetrics = DimeUtils.getWidthMetrics();
        float heightMetrics = DimeUtils.getHeightMetrics();
        AVLoadingIndicatorView avLoadingIndicatorView = LoadCreator.loadIndicate(context, style.name());

        if (window!=null)
        {
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams((int) widthMetrics/METRICS_SCALE,(int)heightMetrics/METRICS_SCALE);
//            window.setLayout((int) widthMetrics/METRICS_SCALE,(int)heightMetrics/METRICS_SCALE);
            appCompatDialog.addContentView(avLoadingIndicatorView,params);
            APP_COMPAT_DIALOGS.add(appCompatDialog);
            appCompatDialog.show();
        }
    }

    //默认的loader
    public static void showLoading(Context context)
    {
        showLoading(context,LoadStyle.BallClipRotatePulseIndicator);
    }

    public static void stopLoading()
    {
        for (AppCompatDialog dialog:APP_COMPAT_DIALOGS) {
            if (dialog.isShowing())
            {
                dialog.cancel();
            }
        }
    }
}
