package com.example.wz1.ec.shop.main.index;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;


/**
 * Created by wz on 2018/9/16.
 */

public class SwipeHandle implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;

    public void initSwipeRefresh(SwipeRefreshLayout layout){
        this.swipeRefreshLayout=layout;
        this.swipeRefreshLayout.setRefreshing(true);
    }

    public void endSwipeRefresh()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onRefresh() {

    }
}
