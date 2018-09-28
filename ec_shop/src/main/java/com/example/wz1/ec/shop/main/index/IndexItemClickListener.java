package com.example.wz1.ec.shop.main.index;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.shop.detail.DetailDelegate;

/**
 * Created by wz on 2018/9/28.
 */

public class IndexItemClickListener extends SimpleClickListener {
    private final BaseDelegate baseDelegate;
    private IndexItemClickListener(BaseDelegate baseDelegate) {
        this.baseDelegate = baseDelegate;
    }

    public static IndexItemClickListener create(BaseDelegate delegate){
        return new IndexItemClickListener(delegate);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        baseDelegate.start(new DetailDelegate());
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
