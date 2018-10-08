package com.example.wz1.ec.shop.main.cart;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wz1.ec.core.app.ECApp;
import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;
import com.example.wz1.ec.core.ui.recycle.MultipleRecycleAdapter;
import com.example.wz1.ec.core.ui.recycle.MultipleViewHolder;
import com.example.wz1.ec.core.utils.glide.GlideUtils;
import com.example.wz1.ec.shop.R;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

/**
 * Created by wz on 2018/10/7.
 */

public class ShopCartRecycleAdapter extends MultipleRecycleAdapter{

    public double getTotalPrice() {
        return mTotalPrice;
    }

    private double mTotalPrice=0.00;

    public void setSelectAll(boolean mSelectAll) {
        this.mSelectAll = mSelectAll;
    }

    private boolean mSelectAll=false;

    protected ShopCartRecycleAdapter(List<MultipleItemEntity> data) {
        super(data);
        for (MultipleItemEntity item:data
             ) {
           double price= item.getField(ShopCartFieldType.price);
           int count = item.getField(ShopCartFieldType.count);
           mTotalPrice+= price*count;
        }
        addItemType(ShopCartItemType.SHOP_CART_ITEM_TYPE, R.layout.item_shop_cart);
    }

    @Override
    protected void convert(MultipleViewHolder holder, final MultipleItemEntity item) {
        switch (holder.getItemViewType())
        {
            case ShopCartItemType.SHOP_CART_ITEM_TYPE:
                String title = (String) item.getField(ShopCartFieldType.title);
                String desc = (String) item.getField(ShopCartFieldType.desc);
                Double price = (Double) item.getField(ShopCartFieldType.price);
                int count = (Integer) item.getField(ShopCartFieldType.count);
                String thumb = (String) item.getField(MultipleFields.IMAGE_URL);
                Integer id = (Integer) item.getField(ShopCartFieldType.id);

                final AppCompatImageView imgThumb = holder.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = holder.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = holder.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = holder.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = holder.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = holder.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = holder.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = holder.getView(R.id.icon_item_shop_cart);

                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvPrice.setText(String.valueOf(price));
                tvCount.setText(String.valueOf(count));

                GlideUtils.loadDefaultNoAnim(thumb,imgThumb,false, DecodeFormat.DEFAULT, DiskCacheStrategy.ALL);
                item.setFields(ShopCartFieldType.is_select,mSelectAll);
                boolean isSelect = item.getField(ShopCartFieldType.is_select);
                if (isSelect)
                {
                    iconIsSelected.setTextColor(ECApp.getApplicationContext().getResources().getColor(R.color.app_main));
                }else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }

                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isSelect= item.getField(ShopCartFieldType.is_select);
                        if (isSelect)
                        {
                            iconIsSelected.setTextColor(Color.GRAY);
                            item.setFields(ShopCartFieldType.is_select,false);
                        }else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(ECApp.getApplicationContext(),R.color.app_main));
                            item.setFields(ShopCartFieldType.is_select,true);
                        }
                    }
                });

                break;
                default:

                    break;
        }
    }
}
