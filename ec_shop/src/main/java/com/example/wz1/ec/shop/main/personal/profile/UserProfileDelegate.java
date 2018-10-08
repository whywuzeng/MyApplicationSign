package com.example.wz1.ec.shop.main.personal.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;
import com.example.wz1.ec.shop.main.personal.list.ListAdapter;
import com.example.wz1.ec.shop.main.personal.list.ListItemBean;
import com.example.wz1.ec.shop.main.personal.list.ListItemType;
import com.example.wz1.ec.shop.main.personal.settings.NameDelegate;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal.profile
 */

public class UserProfileDelegate extends ECAppDelegate {

    @BindView(R2.id.rv_user_profile)
    RecyclerView rvUserProfile;

    @Override
    public Object setLayout() {
        return R.layout.delegate_user_profile;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        ListItemBean avator=new ListItemBean()
                .setmItemType(ListItemType.ITEM_AVATOR)
                .setmId(1)
                .setmImageUrl("http://i9.qhimg.com/t017d891ca365ef60b5.jpg");

        ListItemBean name = new ListItemBean()
                .setmItemType(ListItemType.ITEM_NORMAL)
                .setmId(2)
                .setDelegate(new NameDelegate())
                .setmText("姓名")
                .setmValue("未设置姓名");

        ListItemBean sex = new ListItemBean()
                .setmItemType(ListItemType.ITEM_NORMAL)
                .setmId(3)
                .setmText("性别")
                .setmValue("未设置性别");
        ListItemBean brithday = new ListItemBean()
                .setmItemType(4)
                .setmText("生日")
                .setmValue("未设置生日");

        List<ListItemBean> beans=new ArrayList<>();
        beans.add(avator);
        beans.add(name);
        beans.add(sex);
        beans.add(brithday);

        rvUserProfile.setLayoutManager(new LinearLayoutManager(_mActivity));
        ListAdapter listAdapter = new ListAdapter(beans);
        rvUserProfile.setAdapter(listAdapter);
        rvUserProfile.addOnItemTouchListener(new UserProfileItemListener(this));

    }

}
