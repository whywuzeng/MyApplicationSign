package com.example.wz1.ec.shop.main.index;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;


/**
 * Created by wz on 2018/9/16.
 */

public class SwipeHandle implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;

    public void initSwipeRefresh(SwipeRefreshLayout layout){
        this.swipeRefreshLayout=layout;
        swipeRefreshLayout.setOnRefreshListener(this);
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
                        IndexDataConverter indexDataConverter = new IndexDataConverter();
                        indexDataConverter.setJsonData(result);
                        ArrayList<MultipleItemEntity> itemEntityList = indexDataConverter.getItemEntityList();
                        itemEntityList.get(1).getField(MultipleFields.IMAGE_URL);
                        ToastUtils.showShort((String) itemEntityList.get(1).getField(MultipleFields.IMAGE_URL));
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
