package com.example.wz1.ec.shop.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wz1.ec.core.delegate.CheckDelegate;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.IFailure;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by wz on 2018/9/28.
 */

public class SortContentDelegate extends CheckDelegate {

    private static final String ARG_CONTENT_ID = "arg_content_id";
    @BindView(R2.id.recy_sortcontent)
    RecyclerView recySortcontent;
    private int contentId;

    public static SortContentDelegate newInstance(int contentId) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_CONTENT_ID, contentId);
        final SortContentDelegate sortContentDelegate = new SortContentDelegate();
        sortContentDelegate.setArguments(bundle);
        return sortContentDelegate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            contentId = (int) arguments.getInt(ARG_CONTENT_ID);
        }
        ToastUtils.showLong(String.valueOf(contentId));
    }

    @Override
    public Object setLayout() {
        return R.layout.delagate_sortcontent_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        initRecycleView();
    }

    private void initRecycleView() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recySortcontent.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient build = new RestClient.RestClientBuild()
                .context(_mActivity)
                .url("sort_content_data_1.json")
                .sucess(new ISucess() {
                    @Override
                    public void onSucess(String result) {
                        ArrayList<BaseSection> arrayListItem = SortContentDataConvert.create().getArrayListItem(result);
                        SortContentDataAdapter sortContentDataAdapter = new SortContentDataAdapter(R.layout.sort_content_item, R.layout.sort_content_head, arrayListItem);
                        recySortcontent.setAdapter(sortContentDataAdapter);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(Throwable t) {

                    }
                })
                .build();
        build.get();

    }
}
