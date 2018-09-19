package com.example.wz1.ec.core.delegate.anim;

import android.animation.ObjectAnimator;
import android.view.View;

import com.blankj.utilcode.util.ScreenUtils;

/**
 * Created by Administrator on 2018-09-14.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.delegate.anim
 */

public class BottomAnim {

    private final View BottomView;
    private ObjectAnimator translationY;
    private ObjectAnimator translation1Y;

    public BottomAnim(View bottomView) {
        BottomView = bottomView;
        initAnim();
    }

    //1.初始化动画
    public void initAnim(){
        int bottom = BottomView.getBottom();
        int height = BottomView.getHeight();
        int screenHeight = ScreenUtils.getScreenHeight();
        //出去
         translationY = ObjectAnimator.ofFloat(BottomView, "translationY", 0, -10,height).setDuration(1000);

         //进来
        translation1Y = ObjectAnimator.ofFloat(BottomView, "translationY", height,-10,0).setDuration(1000);
    }

    public void startOut(){
        if (!translationY.isRunning())
        {
            translationY.start();
        }
    }

    public void startIn()
    {
        if (!translation1Y.isRunning())
        {
            translation1Y.start();
        }
    }

    public static BottomAnim build(View view){
        return new BottomAnim(view);
    }
}
