package com.example.wz1.ec.core.ui.recycle;

import android.support.annotation.ColorRes;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by wz on 2018/9/27.
 */

public class BaseItemDecoration extends DividerItemDecoration {

    public BaseItemDecoration(@ColorRes int color, int size) {
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseItemDecoration create(int color,int size)
    {
        return new BaseItemDecoration(color,size);
    }
}
