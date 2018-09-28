package com.example.wz1.ec.core.delegate;

/**
 * 检查6.0的权限操作 delegate
 * Created by wz on 2018/9/1.
 */
public abstract class CheckDelegate extends BaseDelegate {

    public <T extends BaseDelegate> T getParentDelegate(){

        T parentFragment = (T) getParentFragment();
        return parentFragment;
    }
}
