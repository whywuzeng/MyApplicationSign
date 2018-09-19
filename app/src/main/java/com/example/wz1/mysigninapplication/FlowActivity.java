package com.example.wz1.mysigninapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.base.MySupportActivity;


/**
 * Created by Administrator on 2018-08-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication
 */

public class FlowActivity extends MySupportActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flowactivity_layout);
        HomeFragment fragment = findFragment(HomeFragment.class);
        if (fragment==null)
        {
            loadRootFragment(R.id.fl_container,HomeFragment.newInstance());
        }
    }
}
