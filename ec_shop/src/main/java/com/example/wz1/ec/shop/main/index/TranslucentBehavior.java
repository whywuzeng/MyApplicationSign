package com.example.wz1.ec.shop.main.index;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.example.wz1.ec.core.ui.recycle.rgbValue;
import com.example.wz1.ec.shop.R;

/**
 * Created by wz on 2018/9/27.
 */

public class TranslucentBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private float top;
    private rgbValue value=rgbValue.create(255,124,2);

    public TranslucentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Toolbar child, View dependency) {

        return dependency.getId()== R.id.rey_index;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull Toolbar child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        top+=dy;
        int height = child.getHeight();
        float scale = top / height;
        float alpha =255*scale;
        if (top>0&&top<height)
        {
            child.setBackgroundColor(Color.argb((int) alpha,value.red(),value.green(),value.yellow()));
        }else if (top>height){
            child.setBackgroundColor(Color.rgb(value.red(),value.green(),value.yellow()));
        }
    }
}
