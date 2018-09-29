package com.example.wz1.ec.shop.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wz1.ec.core.delegate.CheckDelegate;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.core.ui.recycle.MultipleRecycleAdapter;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;

import butterknife.BindView;

/**
 * Created by wz on 2018/9/28.
 */

public class VerticalListDelegate extends CheckDelegate {

    @BindView(R2.id.recy_verticallist)
    RecyclerView recyVerticallist;

    @Override
    public Object setLayout() {
        return R.layout.delegate_verticallist_layout;
    }

    public void initRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        recyVerticallist.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient build = new RestClient.RestClientBuild()
                .url("sort_list_data.json")
                .context(_mActivity)
                .sucess(new ISucess() {
                    @Override
                    public void onSucess(String result) {
                        MultipleRecycleAdapter multipleRecycleAdapter =   VerticalListAdapter.create(new VerticalListDataConverter().setJsonData(result),getParentDelegate());
                        recyVerticallist.setAdapter(multipleRecycleAdapter);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                }).build();

        build.get();
    }
}
