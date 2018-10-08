package com.example.wz1.ec.shop.main.personal.list;

import android.support.v7.widget.AppCompatImageView;
import android.widget.CompoundButton;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wz1.ec.core.utils.glide.GlideUtils;
import com.example.wz1.ec.shop.R;

import java.util.List;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal.list
 */

public class ListAdapter extends BaseMultiItemQuickAdapter<ListItemBean,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ListAdapter(List<ListItemBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_NORMAL, R.layout.arrow_item_layout);
        addItemType(ListItemType.ITEM_AVATOR,R.layout.arrow_item_avatar);
        addItemType(ListItemType.ITEM_SWITCH,R.layout.arrow_switch_layout);
    }

    @Override
    protected void convert(BaseViewHolder holder, ListItemBean item) {
        switch (holder.getItemViewType()) {
            case ListItemType.ITEM_NORMAL:
                holder.setText(R.id.tv_arrow_text,item.getmText());
                holder.setText(R.id.tv_arrow_value,item.getmValue());
                break;
            case ListItemType.ITEM_AVATOR:
                AppCompatImageView avatar = holder.getView(R.id.img_arrow_avatar);
                GlideUtils.loadDefaultNoAnim(item.getmImageUrl(),avatar,false, DecodeFormat.DEFAULT, DiskCacheStrategy.ALL);
                break;
            case ListItemType.ITEM_SWITCH:
                holder.setText(R.id.tv_arrow_switch_text,item.getmText());
                CompoundButton button= holder.getView(R.id.list_item_switch);
                button.setOnCheckedChangeListener(item.getmOnCheckedChangeListener());
                break;
            default:
                break;
        }
    }
}
