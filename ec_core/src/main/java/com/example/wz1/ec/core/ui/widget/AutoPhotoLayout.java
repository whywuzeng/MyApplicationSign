package com.example.wz1.ec.core.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.wz1.ec.core.R;
import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.core.utils.glide.GlideUtils;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-10-11.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.ui.widget
 */

public class AutoPhotoLayout extends LinearLayout {

    private static final String ADD_ICON="{fa-plus}";
    private static final int ICON_SIZE=20;

    //一行view集合
    private List<View> lineViews= null;
    //所有的View集合 行数
    private List<List<View>> AllViewNums= new ArrayList<>();

    //所有行的不同高度
    private List<Integer> lineHeights=new ArrayList<>();

    //一行有多少个图片
    private  int maxLineCount=4;

    //总共有多个图片
    private int maxImgCount = 12;

    //图片的对象
    private AppCompatImageView mAppCompatImageView;


    private AlertDialog alertDialog;
    private ECAppDelegate ecAppDelegate;
    private boolean isOnceInitOnMeasure=false;
    private LayoutParams perIconWidth;
    private boolean isOnceInitOnLayout=false;
    private IconTextView addIcon;
    //图片的ID
    private int mCurrentNum;

    public AutoPhotoLayout(Context context) {
        this(context,null);
    }

    public AutoPhotoLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AutoPhotoLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
         int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
         int sizeHeight= MeasureSpec.getSize(heightMeasureSpec);
         int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
         int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

         //warp_content 如何使warp_content要给出所有的高度
        int height=0;
        int width=0;

        int lineHeight=0;
        int lineWidth=0;
        int childWidth=0;
        int childHeight=0;
        final int childCount = getChildCount();
        for (int i=0;i<childCount;i++)
        {
            final View childView = getChildAt(i);

            //先测量子View的宽高
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);

            final MarginLayoutParams layoutParams = (MarginLayoutParams) childView.getLayoutParams();

            childWidth= childView.getWidth()+layoutParams.leftMargin+layoutParams.rightMargin;
            childHeight= childView.getHeight()+layoutParams.topMargin+layoutParams.bottomMargin;
            //换行
            if (lineWidth+childWidth>sizeWidth-getPaddingLeft()-getPaddingRight())
            {
                lineWidth=childWidth;
                lineHeight=Math.max(childHeight,lineHeight);
                height+=childHeight;
            }else {
                //是正常叠加
                lineWidth += childWidth;
                lineHeight = Math.max(childHeight,lineHeight);
                height = childHeight;
            }
            if (i==childCount-1) {
                width = Math.max(lineWidth,childWidth);
                //给下方留出空间
                height+=lineHeight;
            }

            //然后对此布局进行测量，每多一个子布局都会影响父布局的大小
            //除非是精确excel ，其他就要自己来测
            setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth:width+getPaddingLeft()+getPaddingRight(),
                    modeHeight == MeasureSpec.EXACTLY ?sizeHeight:height+getPaddingTop()+getPaddingBottom());

            final int perLineWidth = sizeWidth / maxLineCount;
            //这里要new layoutparams
            if (!isOnceInitOnMeasure)
            {
                perIconWidth = new LayoutParams(perLineWidth, perLineWidth);
                isOnceInitOnMeasure=true;
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        AllViewNums.clear();
        lineHeights.clear();
        final int width = getWidth();
        int lineWidth=0;
        int lineHeight=0;

        if (!isOnceInitOnLayout)
        {
            lineViews=new ArrayList<>();
            isOnceInitOnLayout=true;
        }

        final int childCount = getChildCount();
        for (int i=0;i<childCount;i++)
        {
            final View childAt = getChildAt(i);
            final MarginLayoutParams lp = (MarginLayoutParams) childAt.getLayoutParams();
            final int childWidth = childAt.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            final int childHeight = childAt.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (lineWidth+childWidth>width-getPaddingLeft()-getPaddingRight())
            {
                lineHeights.add(lineHeight);
                AllViewNums.add(lineViews);
                lineViews.clear();
                lineWidth=0;
                lineHeight=childHeight;
            }
            lineWidth+=childWidth;
            lineHeight=Math.max(lineHeight,childHeight);
            lineViews.add(childAt);
        }

        //如果最后一行没满一行 要单独假如
        //循环过后
        lineHeights.add(lineHeight);
        AllViewNums.add(lineViews);
        //设置位置
        //得到最外层
         int left = getPaddingLeft();
         int top = getPaddingTop();
        final int size = AllViewNums.size();
        for (int i=0;i<size;i++)
        {
            final List<View> views = AllViewNums.get(i);
            final Integer perlineHeight = lineHeights.get(i);
            final int size1 = views.size();
            for (int j=0;j<size1;j++)
            {
                final View view = views.get(j);
                final MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();
                final int lt = left + lp.leftMargin;
                final int tp = top + lp.topMargin;
                final int viewWidth = view.getMeasuredWidth();
                final int rt = lt+ viewWidth + lp.rightMargin;
                final int bm =tp+ perlineHeight + lp.bottomMargin;
                view.layout(lt,tp,rt,bm);
                left+=viewWidth+lp.rightMargin;
            }
            top+=perlineHeight;
        }
        addIcon.setLayoutParams(perIconWidth);
        //为什么要放开这个呢
        isOnceInitOnLayout=false;
    }

    public void onCropTarget(Uri uri)
    {
        createImageView();
        GlideUtils.loadDefault(uri,mAppCompatImageView,false, DecodeFormat.DEFAULT, DiskCacheStrategy.ALL);
    }

    private void createImageView() {
        mAppCompatImageView=new AppCompatImageView(ecAppDelegate.getContext());
        mAppCompatImageView.setBackgroundResource(R.drawable.text_border);
        mAppCompatImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mAppCompatImageView.setLayoutParams(perIconWidth);
        mAppCompatImageView.setId(mCurrentNum);
        mAppCompatImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final int deleteId = v.getId();
                alertDialog.show();
                final Window window = alertDialog.getWindow();
                if (window!=null)
                {
                    window.setContentView(R.layout.dialog_image_click_panel);
                    window.setGravity(Gravity.BOTTOM);
                    window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    WindowManager.LayoutParams params = window.getAttributes();
                    params.width = WindowManager.LayoutParams.MATCH_PARENT;
                    params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    params.dimAmount = 0.5f;
                    window.setAttributes(params);
                    window.findViewById(R.id.dialog_image_clicked_btn_delete).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final AppCompatImageView deleteImageview = (AppCompatImageView) findViewById(deleteId);
                            AutoPhotoLayout.this.removeView(deleteImageview);
                            final AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
                            alphaAnimation.setDuration(2000);
                            alphaAnimation.setFillAfter(true);
                            alphaAnimation.setRepeatCount(0);
                            alphaAnimation.setStartOffset(0);
                            deleteImageview.setAnimation(alphaAnimation);

                            alphaAnimation.start();
                            mCurrentNum-=1;
                            if (mCurrentNum<maxImgCount)
                            {
                                addIcon.setVisibility(VISIBLE);
                            }
                            alertDialog.cancel();
                        }
                        });
                    window.findViewById(R.id.dialog_image_clicked_btn_undetermined).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });
                    window.findViewById(R.id.dialog_image_clicked_btn_cancel).setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });

                }
            }
        });

        addView(mAppCompatImageView,mCurrentNum);
        mCurrentNum++;
        if (mCurrentNum>maxImgCount)
        {
            addIcon.setVisibility(GONE);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initAddIcon();
        alertDialog = new AlertDialog.Builder(getContext()).create();
    }

    public void setDelegate(ECAppDelegate delegate)
    {
        ecAppDelegate =delegate;
    }

    private void initAddIcon() {
         addIcon = new IconTextView(getContext());
        addIcon.setText(ADD_ICON);
        addIcon.setGravity(Gravity.CENTER);
        addIcon.setBackgroundColor(Color.WHITE);
        addIcon.setTextSize(ICON_SIZE);
        addIcon.setBackgroundResource(R.drawable.text_border);

        addIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ecAppDelegate.startCamera();
            }
        });

        this.addView(addIcon);
    }
}
