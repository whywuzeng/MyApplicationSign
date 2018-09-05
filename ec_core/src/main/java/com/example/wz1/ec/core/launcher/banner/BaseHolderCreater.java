package com.example.wz1.ec.core.launcher.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by Administrator on 2018-09-04.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.launcher.banner
 */

public class BaseHolderCreater implements CBViewHolderCreator<baseImageHolderView> {
    @Override
    public baseImageHolderView createHolder() {

        return new baseImageHolderView();
    }
}
