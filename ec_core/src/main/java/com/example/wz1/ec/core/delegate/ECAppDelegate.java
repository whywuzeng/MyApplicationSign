package com.example.wz1.ec.core.delegate;

/**
 * Created by Administrator on 2018-09-05.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.delegate
 */

public abstract class ECAppDelegate extends CheckDelegate{

    public <T extends BaseDelegate> T getParentDelegate(){

        T parentFragment = (T) getParentFragment();
        return parentFragment;
    }
}
