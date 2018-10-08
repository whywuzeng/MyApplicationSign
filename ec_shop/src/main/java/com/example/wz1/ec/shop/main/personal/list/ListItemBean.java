package com.example.wz1.ec.shop.main.personal.list;

import android.widget.CompoundButton;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.wz1.ec.core.delegate.ECAppDelegate;

/**
 * Created by Administrator on 2018-10-08.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal.list
 */

public class ListItemBean implements MultiItemEntity {

    private int mItemType = 0;
    private ECAppDelegate delegate;
    private String mImageUrl;
    private String mText;

    public int getmItemType() {
        return mItemType;
    }

    public ECAppDelegate getDelegate() {
        return delegate;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmText() {
        return mText;
    }

    public String getmValue() {
        return mValue;
    }

    public int getmId() {
        return mId;
    }

    public CompoundButton.OnCheckedChangeListener getmOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    private String mValue;
    private int mId;
    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = null;

    public ListItemBean() {
    }

    public ListItemBean setmItemType(int mItemType) {
        this.mItemType = mItemType;
        return this;
    }

    public ListItemBean setDelegate(ECAppDelegate delegate) {
        this.delegate = delegate;
        return this;
    }

    public ListItemBean setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
        return this;
    }

    public ListItemBean setmText(String mText) {
        this.mText = mText;
        return this;
    }

    public ListItemBean setmValue(String mValue) {
        this.mValue = mValue;
        return this;
    }

    public ListItemBean setmId(int mId) {
        this.mId = mId;
        return this;
    }

    public ListItemBean setmOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
        return this;
    }

    @Override
    public int getItemType() {
        return getmItemType();
    }
}
