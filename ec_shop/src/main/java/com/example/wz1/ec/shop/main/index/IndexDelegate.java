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
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.core.ui.recycle.BaseItemDecoration;
import com.example.wz1.ec.core.ui.refresh.PagingBean;
import com.example.wz1.ec.core.utils.callback.CallBackListener;
import com.example.wz1.ec.core.utils.callback.CallBackManager;
import com.example.wz1.ec.core.utils.callback.CallBackType;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R2.id.tv_index_scan)
    IconTextView tvIndexScan;

    private SwipeHandle swipeHandle;
    private TextView textView;

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

    public void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(_mActivity, 4);
        reyIndex.setLayoutManager(linearLayoutManager);
        reyIndex.addItemDecoration(BaseItemDecoration.create(ContextCompat.getColor(getContext(), R.color.app_background), ConvertUtils.px2dp(4)));
        BaseDelegate parentDelegate = getParentDelegate();
        reyIndex.addOnItemTouchListener(IndexItemClickListener.create(parentDelegate));
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
        swipeHandle.initSwipeRefresh(srlIndex, reyIndex, new PagingBean(), new IndexDataConverter());
        swipeHandle.firstPage("index.php");
        CallBackManager.getInstances().addCallBack(CallBackType.ON_SCAN, new CallBackListener<String>() {
            @Override
            public void execute(String path) {
                ToastUtils.showShort("得到的二维码是:" + path);
            }
        });

        textView = (TextView) rootView.findViewById(R.id.text);
        new Thread(){
            @Override
            public void run() {
                super.run();
                textView.setText("not from UI thread!!!");
            }
        }.start();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_view, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView actionView = (SearchView) MenuItemCompat.getActionView(item);
    }

    @OnClick(R2.id.tv_index_scan)
    public void onViewClicked() {
        startScanner(this.getParentDelegate());
    }
}

