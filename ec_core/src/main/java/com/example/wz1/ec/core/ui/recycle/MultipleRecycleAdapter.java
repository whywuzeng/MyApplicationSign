package com.example.wz1.ec.core.ui.recycle;

import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wz1.ec.core.R;
import com.example.wz1.ec.core.launcher.banner.ImageHolderCreater;
import com.example.wz1.ec.core.utils.glide.GlideUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wz on 2018/9/25.
 */

public class MultipleRecycleAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> implements BaseQuickAdapter.SpanSizeLookup {

    private boolean isFirst = true;

    protected MultipleRecycleAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE_URL, R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE, R.layout.item_multiple_textimage);
        addItemType(ItemType.BANNAR, R.layout.item_multiple_bannar);
        openLoadAnimation();
        setSpanSizeLookup(this);
        isFirstOnly(true); //第一次加载时显示

    }

    public static MultipleRecycleAdapter create(List<MultipleItemEntity> data) {
        return new MultipleRecycleAdapter(data);
    }

    public static MultipleRecycleAdapter create(DataConverter data) {
        ArrayList<MultipleItemEntity> list = data.getItemEntityList();
        return new MultipleRecycleAdapter(list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity item) {
        final String text;
        final String imageurl;

        //赋值
        switch (holder.getItemViewType()) {
            case ItemType.TEXT:
                text = (String) item.getField(MultipleFields.TEXT);
                holder.setText(R.id.tv_single, text);
                break;
            case ItemType.IMAGE_URL:
                imageurl = (String) item.getField(MultipleFields.IMAGE_URL);
                GlideUtils.loadDefaultTransformation(imageurl, (ImageView) holder.getView(R.id.img_single), false,
                        DecodeFormat.PREFER_ARGB_8888, null, DiskCacheStrategy.ALL);
                break;
            case ItemType.TEXT_IMAGE:
                text = item.getField(MultipleFields.TEXT);
                imageurl = item.getField(MultipleFields.IMAGE_URL);
                holder.setText(R.id.tv_text, text);
                GlideUtils.loadDefaultTransformation(imageurl, (ImageView) holder.getView(R.id.iv_top_img), false,
                        DecodeFormat.PREFER_ARGB_8888, null, DiskCacheStrategy.ALL);
                break;
            case ItemType.BANNAR:
                if (isFirst) {
                    isFirst = !isFirst;
                    ArrayList<String> field = (ArrayList<String>) item.getField(MultipleFields.BANNERS);
                    ConvenientBanner view = (ConvenientBanner) holder.getView(R.id.item_index_banner);
                    view.setPages(new ImageHolderCreater(), field)
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                            .startTurning(3000)
                            .setCanLoop(true);

                }
                break;

            case ItemType.ID:

                break;
            default:
                break;
        }
    }


    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return mData.get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
