package com.example.wz1.ec.core.ui.refresh;

/**
 * Created by wz on 2018/9/26.
 */

public class PagingBean {
    //当前是第几页
    private int mPageIndex=0;

    //总数据
    private int mTotal=0;

    //一页多少条数据
    private int mPageSize=0;

    //总共显示了多少条数据
    private int mCurrentCount=0;

    //加载延迟
    private int mDelay=0;

    public void addIndex(){
        mPageIndex++;
    }

    public int getmPageIndex() {
        return mPageIndex;
    }

    public void setmPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
    }

    public int getmTotal() {
        return mTotal;
    }

    public void setmTotal(int mTotal) {
        this.mTotal = mTotal;
    }

    public int getmPageSize() {
        return mPageSize;
    }

    public void setmPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
    }

    public int getmCurrentCount() {
        return mCurrentCount;
    }

    public void setmCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
    }

    public int getmDelay() {
        return mDelay;
    }

    public void setmDelay(int mDelay) {
        this.mDelay = mDelay;
    }
}
