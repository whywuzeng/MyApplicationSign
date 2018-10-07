package com.example.wz1.ec.shop.main.cart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.wz1.ec.core.app.ECApp;
import com.example.wz1.ec.core.delegate.bottom.BaseItemBottomDelegate;
import com.example.wz1.ec.core.net.RestClient;
import com.example.wz1.ec.core.net.back.IError;
import com.example.wz1.ec.core.net.back.ISucess;
import com.example.wz1.ec.core.ui.recycle.DataConverter;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz 购物车
 * <p>
 * com.example.wz1.ec.shop.main.cart
 */

public class CartDelegate extends BaseItemBottomDelegate {

    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.content_recycle)
    RecyclerView contentRecycle;
    @BindView(R2.id.icon_shop_cart_select_all)
    IconTextView iconShopCartSelectAll;
    @BindView(R2.id.tv_shop_cart_all_select)
    AppCompatTextView tvShopCartAllSelect;
    @BindView(R2.id.tv_shop_cart_count)
    AppCompatTextView tvShopCartCount;
    @BindView(R2.id.tv_shop_cart_number)
    AppCompatTextView tvShopCartNumber;
    @BindView(R2.id.tv_shop_cart_pay)
    AppCompatTextView tvShopCartPay;
    @BindView(R2.id.tv_shop_cart_clear)
    AppCompatTextView tvShopCartClear;
    @BindView(R2.id.tv_shop_cart_remove_selected)
    AppCompatTextView tvShopCartRemoveSelected;
    private ShopCartRecycleAdapter multipleRecycleAdapter;
    private int mCurrent;
    private int mTotalCount;

    @Override
    public Object setLayout() {

        return R.layout.cart_delegate_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        iconShopCartSelectAll.setTag(0);
        RestClient shop_cart_data = new RestClient.RestClientBuild()
                .url("shop_cart_data")
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {

                    }
                }).sucess(new ISucess() {
                    @Override
                    public void onSucess(String result) {
                        try {
                            DataConverter dataConverter = new CartDataConvert().setJsonData(result);
                            multipleRecycleAdapter = new ShopCartRecycleAdapter(dataConverter.getItemEntityList());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mTotalCount=multipleRecycleAdapter.getItemCount();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        contentRecycle.setLayoutManager(linearLayoutManager);
                        contentRecycle.setAdapter(multipleRecycleAdapter);
                        double totalPrice = multipleRecycleAdapter.getTotalPrice();
                        tvShopCartNumber.setText(String.valueOf(totalPrice));
                    }
                }).build();
        shop_cart_data.get();
    }

    @OnClick(R2.id.icon_shop_cart_select_all)
    public void onIconShopCartSelectAllClicked() {
        int tag = (int) iconShopCartSelectAll.getTag();
        if (tag ==0)
        {
            iconShopCartSelectAll.setTag(1);
            multipleRecycleAdapter.setSelectAll(true);
            iconShopCartSelectAll.setTextColor(ContextCompat.getColor(ECApp.getApplicationContext(),R.color.app_toolbar_background));
            multipleRecycleAdapter.notifyItemRangeChanged(0,multipleRecycleAdapter.getItemCount());
        }else {
            iconShopCartSelectAll.setTag(0);
            multipleRecycleAdapter.setSelectAll(false);
            iconShopCartSelectAll.setTextColor(Color.GRAY);
            multipleRecycleAdapter.notifyItemRangeChanged(0,multipleRecycleAdapter.getItemCount());

        }
    }

    @OnClick(R2.id.tv_shop_cart_pay)
    public void onTvShopCartPayClicked() {
    }


    @OnClick(R2.id.tv_shop_cart_clear)
    public void onTvShopCartClearClicked() {
        multipleRecycleAdapter.getData().clear();
        multipleRecycleAdapter.notifyDataSetChanged();
    }

    @OnClick(R2.id.tv_shop_cart_remove_selected)
    public void onTvShopCartRemoveSelectedClicked() {
        List<MultipleItemEntity> data = multipleRecycleAdapter.getData();
        int size = data.size();
        List<MultipleItemEntity> deleteList=new ArrayList<>();
        int removePosition=0;
        for (int i=0;i<size;i++)
        {
            if (data.get(i).getField(ShopCartFieldType.is_select))
            {
                deleteList.add(data.get(i));
            }
        }

        int size1 = deleteList.size();
        for (int i=0;i<size1;i++)
        {
            removePosition= deleteList.get(i).getField(ShopCartFieldType.position);
             //这个position最后一个
              if (removePosition>mCurrent-1&&mCurrent>0)
              {
                   removePosition=removePosition- (mTotalCount-mCurrent);
              }
              if (removePosition<=multipleRecycleAdapter.getItemCount()) {
                  multipleRecycleAdapter.remove(removePosition);
                  mCurrent = multipleRecycleAdapter.getItemCount();
                  multipleRecycleAdapter.notifyItemRangeChanged(removePosition, multipleRecycleAdapter.getItemCount());
              }
        }
    }
}
