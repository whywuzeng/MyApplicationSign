package com.example.wz1.ec.core.launcher.banner;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;
import com.blankj.utilcode.util.ConvertUtils;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wz1.ec.core.utils.glide.GlideUtils;

/**
 * Created by wz on 2018/9/26.
 */

public class ImageHolder implements Holder<String> {

    private AppCompatImageView appCompatImageView;

    @Override
    public View createView(Context context) {
         appCompatImageView = new AppCompatImageView(context);
        return appCompatImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideUtils.loadDefaultOverrideNoAnim(data, appCompatImageView, ConvertUtils.px2dp(720f),ConvertUtils.px2dp(360f),false,
                DecodeFormat.PREFER_ARGB_8888,  DiskCacheStrategy.ALL);

//        Glide.with(context)
//                .load(data)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .dontAnimate()
//                .centerCrop()
//                .into(appCompatImageView);
    }
}
