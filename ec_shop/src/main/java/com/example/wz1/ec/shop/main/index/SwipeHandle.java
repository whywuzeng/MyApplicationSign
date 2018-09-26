package com.example.wz1.ec.shop.main.index;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.core.ui.recycle.DataConverter;
import com.example.wz1.ec.core.ui.recycle.MultipleRecycleAdapter;
import com.example.wz1.ec.core.ui.refresh.PagingBean;


/**
 * Created by wz on 2018/9/16.
 */

public class SwipeHandle implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private PagingBean bean;
    private MultipleRecycleAdapter multipleRecycleAdapter;
    private DataConverter dataConverter;
    private RecyclerView recyclerView;

    public void initSwipeRefresh(SwipeRefreshLayout layout, RecyclerView recyclerView, PagingBean bean , DataConverter converter){
        this.swipeRefreshLayout=layout;
        swipeRefreshLayout.setOnRefreshListener(this);
        this.bean=bean;
        this.dataConverter=converter;
        this.recyclerView=recyclerView;
    }

    public void endSwipeRefresh()
    {
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);

            }
        },2000);
    }

    public void firstPage(String url)
    {
        RestClient build = new RestClient.RestClientBuild().url(url)
                .sucess(new ISucess() {
                    @Override
                    public void onSucess(String result) {
                        JSONObject jsonObject = JSON.parseObject(result);
                        bean.setmTotal(jsonObject.getInteger("total"));
                        bean.setmPageSize(jsonObject.getInteger("page_size"));
                        multipleRecycleAdapter=MultipleRecycleAdapter.create(dataConverter.setJsonData(result));
                        recyclerView.setAdapter(multipleRecycleAdapter);
                    }
                }).failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {

                    }
                }).error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                }).build();
        build.get();
    }

    @Override
    public void onRefresh() {
        endSwipeRefresh();
    }


}
