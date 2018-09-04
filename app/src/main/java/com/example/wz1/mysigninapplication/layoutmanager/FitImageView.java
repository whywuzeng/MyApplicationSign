package com.example.wz1.mysigninapplication.layoutmanager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2018-09-03.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication.layoutmanager
 */

public class FitImageView extends AppCompatImageView {

    public FitImageView(Context context) {
        super(context);
    }

    public FitImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Drawable drawable = getDrawable();
        if (drawable!=null)
        {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            float intrinsicHeight = drawable.getIntrinsicHeight();
            float intrinsicWidth = drawable.getIntrinsicWidth();
            float tmpHeight= Math.max(intrinsicHeight,intrinsicWidth);
            float tmpWidth=Math.min(intrinsicHeight,intrinsicWidth);
            int height = (int) Math.ceil((float) width *  tmpHeight/tmpWidth );
            setMeasuredDimension(width,height);
        }else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }
}
