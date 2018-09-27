package com.example.wz1.ec.shop.main.index;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.blankj.utilcode.util.ConvertUtils;
import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.core.ui.recycle.BaseItemDecoration;
import com.example.wz1.ec.core.ui.refresh.PagingBean;
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
    private static final String TAG = "IndexDelegate";
    @BindView(R2.id.rey_index)
    RecyclerView reyIndex;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout srlIndex;
    @BindView(R2.id.tb_index)
    Toolbar tbIndex;

    private SwipeHandle swipeHandle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @SuppressLint("ResourceAsColor")
    public void initSwipeRefresh() {
        srlIndex.setColorSchemeColors(
                android.R.color.holo_blue_dark,
                android.R.color.holo_red_dark
        );
        srlIndex.setProgressViewOffset(true, 80, 120);
    }

    public void initRecycleView(){
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(_mActivity,4);
        reyIndex.setLayoutManager(linearLayoutManager);
        reyIndex.addItemDecoration(BaseItemDecoration.create(ContextCompat.getColor(getContext(),R.color.app_backgroud), ConvertUtils.px2dp(4)));
    }

    @Override
    public Object setLayout() {

        return R.layout.index_delegate_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        initSwipeRefresh();
        initRecycleView();
        swipeHandle = new SwipeHandle();
        swipeHandle.initSwipeRefresh(srlIndex,reyIndex,new PagingBean(),new IndexDataConverter());
        swipeHandle.firstPage("index.php");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_view, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView actionView = (SearchView) MenuItemCompat.getActionView(item);
    }
}

