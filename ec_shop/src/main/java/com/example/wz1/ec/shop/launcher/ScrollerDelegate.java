package com.example.wz1.ec.shop.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.wz1.ec.core.app.AccountManager;
import com.example.wz1.ec.core.app.IUserCheck;
import com.example.wz1.ec.core.delegate.CheckDelegate;
import com.example.wz1.ec.core.launcher.banner.BaseHolderCreater;
import com.example.wz1.ec.core.utils.storage.PrefrencesUtils;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-09-04.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.launcher
 */

public class ScrollerDelegate extends CheckDelegate implements OnItemClickListener {

    private static final String TAG = "ScrollerDelegate";
    @BindView(R2.id.scroller_banner)
    ConvenientBanner<Integer> scrollerBanner;
    private List<Integer> mSrcData;
    private ILauncherFinish finish;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        finish= (ILauncherFinish) activity;
    }

    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
        Log.e(TAG, "onNewBundle: " );
    }

    @Override
    public Object setLayout() {
        return R.layout.scroller_delegate;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        int[] dotRes = initDatas();
        scrollerBanner.setPages(new BaseHolderCreater(), mSrcData)
                .setPageIndicator(dotRes)
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true);
    }

    private int[] initDatas() {
        mSrcData =new ArrayList<>();
        mSrcData.add(R.mipmap.launcher_01);
        mSrcData.add(R.mipmap.launcher_02);
        mSrcData.add(R.mipmap.launcher_03);
        mSrcData.add(R.mipmap.launcher_04);
        mSrcData.add(R.mipmap.launcher_05);
        return new int[]{R.drawable.shape_dot_black,R.drawable.shape_dot_grey};
    }

    @Override
    public void onItemClick(int position) {
        if (position>=mSrcData.size()-1)
        {
            //点到最后一个点
            PrefrencesUtils.setAppFlag(AppLauncherFlag.APP_FIRST_SCORLL_FLAG.name(),true);
            AccountManager.checkAccount(new IUserCheck() {
                @Override
                public void onUserSignIn() {
                    finish.onLauncherFinish(LauncherFinishTag.SIGNED);
                }

                @Override
                public void onUserNoSignIn() {
                    finish.onLauncherFinish(LauncherFinishTag.NO_SIGNUP);
                }
            });

        }else if (position==0)
        {
            // TODO: 2018/9/8 测试 singleTask作用
            getSupportDelegate().start(new ScrollerDelegate(),SINGLETOP);
        }
    }
}
