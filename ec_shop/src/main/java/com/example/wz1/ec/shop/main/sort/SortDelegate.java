package com.example.wz1.ec.shop.main.sort;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.main.sort.content.SortContentDelegate;
import com.example.wz1.ec.shop.main.sort.list.VerticalListDelegate;

/**
 * Created by wz on 2018/9/28.
 */

public class SortDelegate extends BaseItemBottomDelegate {
    @Override
    public Object setLayout() {
        return R.layout.sort_delegate_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        loadRootFragment(R.id.vertical_list_container,new VerticalListDelegate());
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, SortContentDelegate.newInstance(1));
    }
}
