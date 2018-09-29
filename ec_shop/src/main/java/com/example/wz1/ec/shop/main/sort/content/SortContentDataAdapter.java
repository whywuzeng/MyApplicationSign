package com.example.wz1.ec.shop.main.sort.content;

import android.widget.ImageView;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.example.wz1.ec.core.utils.glide.GlideUtils;
import com.example.wz1.ec.shop.R;

import java.util.List;

/**
 * Created by wz on 2018/9/29.
 */

public class SortContentDataAdapter extends BaseSectionQuickAdapter<BaseSection,SortSectionViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SortContentDataAdapter(int layoutResId, int sectionHeadResId, List<BaseSection> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(SortSectionViewHolder helper, BaseSection item) {
        helper.setText(R.id.tv_headname,item.header);

    }

    @Override
    protected void convert(SortSectionViewHolder helper, BaseSection item) {
        helper.setText(R.id.tv_content_section,item.t.goodsName);
        GlideUtils.loadDefault(item.t.goodsThumb,(ImageView) helper.getView(R.id.iv_thmub),false, DecodeFormat.DEFAULT, DiskCacheStrategy.ALL);
    }
}
