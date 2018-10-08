package com.example.wz1.ec.shop.main.personal;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.shop.main.personal.list.ListItemBean;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal
 */

class PersonalClickListener extends SimpleClickListener {

    private final BaseDelegate baseDelegate;

    public PersonalClickListener(BaseDelegate delegate)
    {
        this.baseDelegate =delegate;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ListItemBean listItemBean = (ListItemBean) adapter.getData().get(position);
        switch (position)
        {
            case 0:
                baseDelegate.start(listItemBean.getDelegate());
                break;
            case 1:
                baseDelegate.start(listItemBean.getDelegate());
                break;
            default:

                break;
        }
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
