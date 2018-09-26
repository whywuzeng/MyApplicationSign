package com.example.wz1.ec.core.ui.recycle;

import android.support.v7.widget.GridLayoutManager;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wz1.ec.core.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wz on 2018/9/25.
 */

public class MultipleRecycleAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> implements BaseQuickAdapter.SpanSizeLookup{


    protected MultipleRecycleAdapter(List<MultipleItemEntity> data) {
        super(data);
        init();
    }

    private void init() {
        addItemType(ItemType.TEXT, R.layout.item_multiple_text);
        addItemType(ItemType.IMAGE_URL,R.layout.item_multiple_image);
        addItemType(ItemType.TEXT_IMAGE,R.layout.item_multiple_textimage);
    }

    public static MultipleRecycleAdapter create(List<MultipleItemEntity> data){
         return new MultipleRecycleAdapter(data);
    }

    public static MultipleRecycleAdapter create(DataConverter data)
    {
        ArrayList<MultipleItemEntity> list = data.getItemEntityList();
        return new MultipleRecycleAdapter(list);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        //赋值

    }

    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return mData.get(position).getField(MultipleFields.SPAN_SIZE);
    }
}
