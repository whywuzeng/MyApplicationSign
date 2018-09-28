package com.example.wz1.ec.shop.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.CheckDelegate;
import com.example.wz1.ec.shop.R;

/**
 * Created by wz on 2018/9/28.
 */

public class DetailDelegate extends CheckDelegate {
    @Override
    public Object setLayout() {

        return R.layout.delegate_detail;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

    }
}
