package com.example.wz1.ec.shop.main.sort.list;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.example.wz1.ec.core.delegate.BaseDelegate;
import com.example.wz1.ec.core.ui.recycle.DataConverter;
import com.example.wz1.ec.core.ui.recycle.ItemType;
import com.example.wz1.ec.core.ui.recycle.MultipleFields;
import com.example.wz1.ec.core.ui.recycle.MultipleItemEntity;
import com.example.wz1.ec.core.ui.recycle.MultipleRecycleAdapter;
import com.example.wz1.ec.core.ui.recycle.MultipleViewHolder;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.main.sort.content.SortContentDelegate;

import java.util.ArrayList;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * Created by wz on 2018/9/28.
 */

public class VerticalListAdapter extends MultipleRecycleAdapter{

    private int prePosition=0;
    private BaseDelegate delegate;

    protected VerticalListAdapter(ArrayList<MultipleItemEntity> data, BaseDelegate delegate) {
        super(data);
        addItemType(ItemType.VERTICAL_LIST, R.layout.item_verticallist);
        this.delegate=delegate;
    }

    protected static VerticalListAdapter create(DataConverter data, BaseDelegate delegate) {
        ArrayList<MultipleItemEntity> itemEntityList = null;
        try {
            itemEntityList = data.getItemEntityList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new VerticalListAdapter(itemEntityList,delegate);
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
                    holder.itemView.setBackgroundColor(ContextCompat.getColor(mContext,R.color.s24spcolor));
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

                        Integer fieldid = (Integer) item.getField(MultipleFields.ID);
                        SortContentDelegate contentDelegate = SupportHelper.findFragment(delegate.getChildFragmentManager(), SortContentDelegate.class);
                        if (contentDelegate!=null)
                        {
                            contentDelegate.getSupportDelegate().replaceFragment(SortContentDelegate.newInstance(fieldid),false);
                        }

                    }
                });
                break;
            default:

                break;
        }
    }
}
