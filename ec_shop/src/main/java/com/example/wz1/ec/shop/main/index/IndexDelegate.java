package com.example.wz1.ec.shop.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz 购物车
 * <p>
 * com.example.wz1.ec.shop.main.cart
 */

public class IndexDelegate extends BaseItemBottomDelegate {

    @BindView(R2.id.recy_index)
    RecyclerView recyIndex;
    @BindView(R2.id.searchView)
    AppCompatEditText searchView;
    @BindView(R2.id.toolbar_index)
    Toolbar toolbarIndex;
    @BindView(R2.id.swipe_index)
    SwipeRefreshLayout swipeIndex;

    @Override
    public Object setLayout() {

        return R.layout.index_delegate_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

    }

}
