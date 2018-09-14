package com.example.wz1.ec.core.delegate.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;

import com.example.wz1.ec.core.R;
import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import me.yokeyword.fragmentation.Base.MySupportFragment;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.delegate.bottom
 */

public abstract class BaseBottomDelegate extends ECAppDelegate {
    private final static ArrayList<TableBean> ITEM = new ArrayList<>();
    private final static ArrayList<BaseItemBottomDelegate> ITEM_BOTTOM_DELEGATES = new ArrayList<>();
    private final static ArrayList<IconTextView> ITEM_ICONBUTTON = new ArrayList<>();
    private ContentFrameLayout contentLayout;
    private int mCurrentPosition;
    private int mIndexDelegate = 0;

    //1:初始 tabbean  得到linkedhashmap 数据
    protected abstract LinkedHashMap<TableBean, BaseItemBottomDelegate> getItem();

    public void initTabData() {
        TableBeanBuild build = TableBeanBuild.build();
        build.addTables(getItem());

        LinkedHashMap<TableBean, BaseItemBottomDelegate> item = build.getItem();
        for (Map.Entry<TableBean, BaseItemBottomDelegate> value : item.entrySet()) {
            ITEM.add(value.getKey());
            ITEM_BOTTOM_DELEGATES.add(value.getValue());
        }
        mIndexDelegate = setIndex();
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom_layout;
    }

    protected abstract int setIndex();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTabData();
    }

    //2：来代码生成布局
    public void generateLayout() {
        View containerID = rootView.findViewById(R.id.item_container);
        contentLayout = (ContentFrameLayout) rootView.findViewById(R.id.rl_container);
        for (final TableBean bean : ITEM) {
            ViewGroup itemLayout = (ViewGroup) getLayoutInflater().inflate(R.layout.bottom_item_icon_text_layout, (ViewGroup) containerID, true);
            LinearLayoutCompat childAt = (LinearLayoutCompat) itemLayout.getChildAt(ITEM.indexOf(bean));
            IconTextView childAt1 = (IconTextView)childAt.getChildAt(0);
            ITEM_ICONBUTTON.add(childAt1);
            childAt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickColor(ITEM.indexOf(bean));
                }
            });
            childAt1.setText(bean.getIcon());
            AppCompatTextView textView = (AppCompatTextView) childAt.getChildAt(1);
            textView.setText(bean.getTitle());
        }
        MySupportFragment[] mySupportFragments = ITEM_BOTTOM_DELEGATES.toArray(new MySupportFragment[]{});
        
        clickColor(mIndexDelegate);
        getSupportDelegate().loadMultipleRootFragment(R.id.rl_container, mIndexDelegate, mySupportFragments);
    }

    private void clickColor(int position) {
        for (int i = 0; i < ITEM_ICONBUTTON.size(); i++) {
            if (i != position) {
                ((IconTextView) ITEM_ICONBUTTON.get(position)).setTextColor(Color.GRAY);
            }
            else {
                ((IconTextView) ITEM_ICONBUTTON.get(position)).setTextColor(Color.RED);
            }
        }
        clickLayout(position);
    }

    //3:加入主布局逻辑
    private void clickLayout(int position) {
        getSupportDelegate().showHideFragment(ITEM_BOTTOM_DELEGATES.get(position), ITEM_BOTTOM_DELEGATES.get(mCurrentPosition));
        mCurrentPosition = position;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        generateLayout();
    }
}
