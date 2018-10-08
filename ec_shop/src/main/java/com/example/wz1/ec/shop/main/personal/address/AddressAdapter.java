package com.example.wz1.ec.shop.main.personal.address;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;
import com.example.wz1.ec.core.ui.recycle.MultipleRecycleAdapter;
import com.example.wz1.ec.core.ui.recycle.MultipleViewHolder;
import com.example.wz1.ec.shop.R;

import java.util.List;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal.address
 */

public class AddressAdapter extends MultipleRecycleAdapter {

    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        switch (holder.getItemViewType())
        {
            case AddressItemType.ITEM_ADDRESS:

                final String name = item.getField(MultipleFields.NAME);
                final String phone = item.getField(AddressItemFields.PHONE);
                final String address = item.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = item.getField(AddressItemFields.DEFAULT);
                final int id = item.getField(MultipleFields.ID);

                final AppCompatTextView nameText = holder.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = holder.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = holder.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = holder.getView(R.id.tv_address_delete);
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;

                default:
                    break;
        }
    }
}
