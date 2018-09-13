package com.example.wz1.ec.core.delegate.bottom;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wz1.ec.core.R;
import com.example.wz1.ec.core.delegate.ECAppDelegate;

/**
 * Created by Administrator on 2018-09-13.
 * <p>
 * by author wz fragment 关于Item的
 * <p>
 * com.example.wz1.ec.core.delegate.bottom
 */

public abstract class BaseItemBottomDelegate extends ECAppDelegate {

    private final long TIMEDIFF = 2000;
    private long preTime;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - preTime > TIMEDIFF) {
            ToastUtils.showShort("双击退出" + getString(R.string.app_name));
            preTime = System.currentTimeMillis();
        }
        else {
            _mActivity.onBackPressed();
            if (preTime != 0) {
                preTime = 0;
            }
            return true;
        }
        return super.onBackPressedSupport();
    }
}
