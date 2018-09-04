package com.example.wz1.ec.core.Custom;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018-09-03.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.Custom
 */

public class CustomLayoutManager extends RecyclerView.LayoutManager{

    private int mItemViewWidth;
    private int mItemViewHeight;
    private int mItemCount;
    private int  mScrollOffset = Integer.MAX_VALUE;
    private float mScale = 0.9f;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    //测量Item

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if (state.getItemCount()==0||state.isPreLayout())
            return;

        removeAndRecycleAllViews(recycler);
         mItemViewWidth = (int) (getHorizontalSpace() * 0.84f);
          mItemViewHeight = (int) (mItemViewWidth * 1.46f);

         mItemCount = getItemCount();

         //获取所有高度和  这里是可看见的么？ 在这些值中间

        mScrollOffset= Math.min( Math.max(mItemViewHeight, mScrollOffset) , mItemCount * mItemViewHeight);
       layoutChild(recycler);
    }

    private void layoutChild(RecyclerView.Recycler recycler) {
        if (getItemCount() == 0 ) return;
        //获取到最后一个Item的位置
        int bottomItemPosition = (int) Math.floor(mScrollOffset / mItemViewHeight);
        //获取到出去一个完整的Item的高度，还剩余多少空间
        int remainSpace = getVerticalSpace() - mItemViewHeight;
        //滑动的时候可以获取到最后一个Item在屏幕上还显示的高度
        int bottomItemVisibleHeight = mScrollOffset % mItemViewHeight;
        //最后一个Item显示高度相对于本身的比例
        final float offsetPercentRelativeToItemView = bottomItemVisibleHeight * 1.0f / mItemViewHeight;
        //把我们需要的Item添加到这个集合
        ArrayList<ItemViewInfo> layoutInfos = new ArrayList<>();
        for (int i = bottomItemPosition - 1, j = 1; i >= 0; i--, j++) {
            //计算偏移量
            double maxOffset = (getVerticalSpace() - mItemViewHeight) / 2 * Math.pow(0.8, j);
            //这个Item的top值
            int start = (int) (remainSpace - offsetPercentRelativeToItemView * maxOffset);
            //这个Item需要缩放的比例
            float scaleXY = (float) (Math.pow(mScale, j - 1) * (1 - offsetPercentRelativeToItemView * (1 - mScale)));
            float positonOffset = offsetPercentRelativeToItemView;
            //Item上面的距离占RecyclerView可用高度的比例
            float layoutPercent = start * 1.0f / getVerticalSpace();
            ItemViewInfo info = new ItemViewInfo(start, scaleXY, positonOffset, layoutPercent);
            layoutInfos.add(0, info);
            remainSpace = (int) (remainSpace - maxOffset);
            //在添加Item的同时，计算剩余空间是否可以容下下一个Item，如果不能的话，就不再添加了
            if (remainSpace <= 0) {
                info.setTop((int) (remainSpace + maxOffset));
                info.setPositionOffset(0);
                info.setLayoutPercent(info.getTop() / getVerticalSpace());
                info.setScaleXY((float) Math.pow(mScale, j - 1)); ;
                break;
            }
        }

        if (bottomItemPosition < mItemCount) {
            final int start = getVerticalSpace() - bottomItemVisibleHeight;
            layoutInfos.add(new ItemViewInfo(start, 1.0f, bottomItemVisibleHeight * 1.0f / mItemViewHeight, start * 1.0f / getVerticalSpace())
                    .setIsBottom());
        } else {
            bottomItemPosition = bottomItemPosition - 1;//99
        }
        //这里做的是回收处理
        int layoutCount = layoutInfos.size();
        final int startPos = bottomItemPosition - (layoutCount - 1);
        final int endPos = bottomItemPosition;
        final int childCount = getChildCount();
        for (int i = childCount - 1; i >= 0; i--) {
            View childView = getChildAt(i);
            int pos = getPosition(childView);
            if (pos > endPos || pos < startPos) {
                removeAndRecycleView(childView, recycler);
            }
        }

        detachAndScrapAttachedViews(recycler);
        //这里主要是对需要显示的Item进行排列以及缩放
        for (int i = 0; i < layoutCount; i++) {
            View view = recycler.getViewForPosition(startPos + i);
            ItemViewInfo layoutInfo = layoutInfos.get(i);
            addView(view);
            measureChildWithExactlySize(view);
            int left = (getHorizontalSpace() - mItemViewWidth) / 2;
            layoutDecoratedWithMargins(view, left, layoutInfo.getTop(), left + mItemViewWidth, layoutInfo.getTop() + mItemViewHeight);
            view.setPivotX(view.getWidth() / 2);
            view.setPivotY(0);
            view.setScaleX(layoutInfo.getScaleXY());
            view.setScaleY(layoutInfo.getScaleXY());
        }

    }

    /**
     * 测量itemview的确切大小
     */
    private void measureChildWithExactlySize(View child ) {
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(mItemViewWidth, View.MeasureSpec.EXACTLY);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(mItemViewHeight, View.MeasureSpec.EXACTLY);
        child.measure(widthSpec, heightSpec);
    }

    /**
     * 获得横向的宽度
     */
    public int getHorizontalSpace(){
        return getWidth()+getPaddingLeft()+getPaddingRight();
    }

    /**
     * 获取RecyclerView的显示高度
     */
    public int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int pendingScrollOffset = mScrollOffset + dy;
        mScrollOffset = Math.min(Math.max(mItemViewHeight, mScrollOffset + dy), mItemCount * mItemViewHeight);
        //每次滑动都要对Item进行排列等操作
        layoutChild(recycler);
        return mScrollOffset - pendingScrollOffset + dy;
//        return super.scrollVerticallyBy(dy, recycler, state);
    }
}
