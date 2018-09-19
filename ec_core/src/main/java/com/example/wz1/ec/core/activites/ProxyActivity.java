package com.example.wz1.ec.core.activites;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.wz1.ec.core.R;
import com.example.wz1.ec.core.delegate.BaseDelegate;

import me.yokeyword.fragmentation.base.MySupportActivity;


/**
 * Created by wz on 2018/9/1.
 */

public abstract class ProxyActivity extends MySupportActivity {

    public abstract BaseDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    public void initContainer(@Nullable Bundle savedInstanceState){
        ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container_id);
        setContentView(contentFrameLayout);
        BaseDelegate baseDelegate = setRootDelegate();
        if (savedInstanceState==null&&baseDelegate!=null) {
            loadRootFragment(R.id.delegate_container_id, baseDelegate);
        }
        //实例化布局
        //设置布局
        //loadrootFragment 只是第一次设置布局 其他可以寻找布局
    }
}
