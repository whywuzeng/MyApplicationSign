package com.example.wz1.ec.shop.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;
import com.example.wz1.ec.shop.main.personal.address.AddressDelegate;
import com.example.wz1.ec.shop.main.personal.list.ListAdapter;
import com.example.wz1.ec.shop.main.personal.list.ListItemBean;
import com.example.wz1.ec.shop.main.personal.list.ListItemType;
import com.example.wz1.ec.shop.main.personal.profile.UserProfileDelegate;
import com.example.wz1.ec.shop.main.personal.settings.SettingsDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal
 */

public class PersonalDelegate extends BaseItemBottomDelegate {

    @BindView(R2.id.img_user_avatar)
    CircleImageView imgUserAvatar;
    @BindView(R2.id.tv_all_account_arrow)
    IconTextView tvAllAccountArrow;
    @BindView(R2.id.ll_pay)
    LinearLayout llPay;
    @BindView(R2.id.ll_receive)
    LinearLayout llReceive;
    @BindView(R2.id.ll_evaluate)
    LinearLayout llEvaluate;
    @BindView(R2.id.ll_after_market)
    LinearLayout llAfterMarket;
    @BindView(R2.id.rv_personal_setting)
    RecyclerView rvPersonalSetting;

    @Override
    public Object setLayout() {

        return R.layout.delegate_personal;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        ListItemBean address = new ListItemBean().setmText("收货地址")
                .setDelegate(new AddressDelegate())
                .setmItemType(ListItemType.ITEM_NORMAL)
                .setmId(1);
        ListItemBean settings = new ListItemBean().setmText("系统设置")
                .setDelegate(new SettingsDelegate())
                .setmItemType(ListItemType.ITEM_NORMAL)
                .setmId(2);
        List<ListItemBean> beanList=new ArrayList<>();
        beanList.add(address);
        beanList.add(settings);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(_mActivity);
        rvPersonalSetting.setLayoutManager(linearLayoutManager);
        rvPersonalSetting.setAdapter(new ListAdapter(beanList));
        rvPersonalSetting.addOnItemTouchListener(new PersonalClickListener(this.getParentDelegate()));
    }

    @OnClick(R2.id.img_user_avatar)
    public void onImgUserAvatarClicked() {
        start(new UserProfileDelegate());
    }

    @OnClick(R2.id.tv_all_account_arrow)
    public void onTvAllAccountArrowClicked() {
    }

    @OnClick(R2.id.ll_pay)
    public void onLlPayClicked() {
    }

    @OnClick(R2.id.ll_receive)
    public void onLlReceiveClicked() {
    }

    @OnClick(R2.id.ll_evaluate)
    public void onLlEvaluateClicked() {
    }

    @OnClick(R2.id.ll_after_market)
    public void onLlAfterMarketClicked() {
    }
}
