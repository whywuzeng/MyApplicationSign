package com.example.wz1.ec.shop.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.core.delegate.web.WebDelegateImpl;
import com.example.wz1.ec.shop.R;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz 购物车
 * <p>
 * com.example.wz1.ec.shop.main.cart
 */

public class DiscoverDelegate extends BaseItemBottomDelegate {
    @Override
    public Object setLayout() {

        return R.layout.discover_delegate_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        WebDelegateImpl newIntance = WebDelegateImpl.getNewIntance("index.html");
        newIntance.setTopDelegate((ECAppDelegate) this.getParentDelegate());
        loadRootFragment(R.id.layout_discov,newIntance);
    }
}
