package com.example.wz1.ec.core.ui.recycle;

import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;

/**
 * Created by wz on 2018/9/27.
 */

public class DividerLookupImpl implements DividerItemDecoration.DividerLookup {

    private final int color;
    private final int size;

    public DividerLookupImpl(int color, int size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public Divider getVerticalDivider(int position) {
        return new Divider.Builder()
                .color(color)
                .size(size)
                .build();
    }

    @Override
    public Divider getHorizontalDivider(int position) {
        return new Divider.Builder()
                .color(color)
                .size(size)
                .build();
    }
}
