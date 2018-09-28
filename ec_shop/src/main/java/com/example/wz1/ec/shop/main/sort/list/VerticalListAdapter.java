package com.example.wz1.ec.shop.main.sort.list;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.wz1.ec.core.ui.recycle.ItemType;
import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;
import com.example.wz1.ec.core.ui.recycle.MultipleRecycleAdapter;
import com.example.wz1.ec.core.ui.recycle.MultipleViewHolder;
import com.example.wz1.ec.shop.R;

import java.util.List;

/**
 * Created by wz on 2018/9/28.
 */

public class VerticalListAdapter extends MultipleRecycleAdapter{

    private int prePosition=0;

    protected VerticalListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.VERTICAL_LIST, R.layout.item_verticallist);
    }

    @Override
    protected void convert(final MultipleViewHolder holder, final MultipleItemEntity item) {
        super.convert(holder, item);
        boolean isVisable = (Boolean) item.getField(MultipleFields.TAG);
        switch (item.getItemType()) {
            case ItemType.VERTICAL_LIST:
                View view = holder.getView(R.id.right_side_view);
                holder.setText(R.id.tv_verticaltext,(String)item.getField(MultipleFields.NAME));
                view.setVisibility(isVisable?View.VISIBLE:View.INVISIBLE);
                if (isVisable)
                {
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.item_background));
                }else {
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.app_item_color));
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        item.setFields(MultipleFields.TAG,true);
                        int currentPosition = holder.getAdapterPosition();
                        notifyItemChanged(currentPosition);

                        mData.get(prePosition).setFields(MultipleFields.TAG,false);
                        notifyItemChanged(prePosition);

                        prePosition=currentPosition;
                    }
                });
                break;
            default:

                break;
        }

    }
}
