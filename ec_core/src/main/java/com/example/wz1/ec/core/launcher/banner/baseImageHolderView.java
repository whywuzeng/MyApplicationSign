package com.example.wz1.ec.core.launcher.banner;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * Created by Administrator on 2018-09-04.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.launcher.banner
 */

public class baseImageHolderView implements Holder<Integer> {

    private ImageView mImageView;

    @Override
    public View createView(Context context) {
        mImageView=new ImageView(context);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setImageResource(data);
    }
}
