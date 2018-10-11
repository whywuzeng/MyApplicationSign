package com.example.wz1.ec.core.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.wz1.ec.core.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-10-11.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.ui.widget
 */

public class StarLayout extends LinearLayout implements View.OnClickListener{

    private static final int MAX_COUNT_STARS=5;
    public static final String ICON_SELECT_STAR="{fa-star}";
    public static final String ICON_UN_SELECT_START="{fa-star-o}";
    private final ArrayList<IconTextView> STARS = new ArrayList<>();

    public StarLayout(Context context) {
        this(context,null);
    }

    public StarLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initIcon(context);
    }

    private void initIcon(Context context) {
        for (int i=0;i<MAX_COUNT_STARS;i++)
        {
            final IconTextView iconTextView = new IconTextView(context);
            iconTextView.setGravity(Gravity.CENTER);
            iconTextView.setText(ICON_UN_SELECT_START);
            final LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight=1;
            iconTextView.setLayoutParams(lp);
            //设置id 的唯一标识
            iconTextView.setTag(R.id.star_count,i);
            iconTextView.setTag(R.id.is_star_select,false);
            STARS.add(iconTextView);
            this.addView(iconTextView);
            iconTextView.setOnClickListener(this);
        }
    }

    public int getIconSelectStarCount(){
        int countNum=0;
        for (int i=0;i<STARS.size();i++)
        {
            final IconTextView iconTextView = STARS.get(i);
            if ((Boolean) iconTextView.getTag(R.id.is_star_select))
            {
                countNum++;
            }
        }
        return countNum;
    }

    public  void setIconSelectStar(int count) {
        for (int i=0;i<=count;i++)
        {
            final IconTextView iconTextView = STARS.get(i);
            iconTextView.setText(ICON_SELECT_STAR);
            iconTextView.setTag(R.id.is_star_select,true);
        }
    }

    public void setIconUnSelectStar(int count)
    {
        for (int i=count+1;i<MAX_COUNT_STARS;i++)
        {
            final IconTextView iconTextView = STARS.get(i);
            iconTextView.setText(ICON_UN_SELECT_START);
            iconTextView.setTag(R.id.is_star_select,false);
        }
    }

    @Override
    public void onClick(View v) {
        final int tag = (int) v.getTag(R.id.star_count);
        setIconSelectStar(tag);
        setIconUnSelectStar(tag);
    }
}
