package com.example.wz1.ec.shop.main.cart;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.shop.R;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz 购物车
 * <p>
 * com.example.wz1.ec.shop.main.cart
 */

public class CartDelegate extends BaseItemBottomDelegate {
    @Override
    public Object setLayout() {

        return R.layout.cart_delegate_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

    }
}
