package com.example.wz1.mysigninapplication.Base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.wz1.mysigninapplication.R;

/**
 * Created by Administrator on 2018-08-23.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.mysigninapplication.Base
 */

public class BaseBackFragment extends MySupportFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop();
            }
        });
    }
}
