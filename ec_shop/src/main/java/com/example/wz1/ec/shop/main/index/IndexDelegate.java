package com.example.wz1.ec.shop.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.IRequest;
import com.example.wz1.ec.core.net.back.ISucess;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public Object setLayout() {

        return R.layout.index_delegate_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
//        ActionBar actionBar = ((AppCompatActivity) _mActivity).getSupportActionBar();
//        actionBar.show();

        RestClient build = new RestClient.RestClientBuild().url("")
                .params("", "")
                .context(_mActivity)
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        Log.e(TAG, "onRequestStart:");
                    }

                    @Override
                    public void onRequestComplete() {
                        Log.e(TAG, "onRequestComplete: ");
                    }
                }).error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Log.e(TAG, "onError: " + message);
                    }
                }).failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {
                        Log.e(TAG, "onFailure: " + t.getLocalizedMessage());
                    }
                }).sucess(new ISucess() {
                    @Override
                    public void onSucess(String result) {
//                        Toast.makeText(_mActivity, result, Toast.LENGTH_SHORT).show();

                    }
                }).build();
        build.get();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search_view, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView actionView = (SearchView) MenuItemCompat.getActionView(item);
    }
}
