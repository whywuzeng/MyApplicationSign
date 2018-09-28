package com.example.wz1.ec.shop.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.CheckDelegate;
import com.example.wz1.ec.shop.R;

/**
 * Created by wz on 2018/9/28.
 */

public class SortContentDelegate extends CheckDelegate {

    private static final String ARG_CONTENT_ID = "arg_content_id";
    private int contentId;

    public static SortContentDelegate newInstance(int contentId) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_CONTENT_ID, contentId);
        final SortContentDelegate sortContentDelegate = new SortContentDelegate();
        sortContentDelegate.putNewBundle(bundle);
        return sortContentDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments!=null)
        {
            contentId = (int) arguments.getInt(ARG_CONTENT_ID);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delagate_sortcontent_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {

    }
}
