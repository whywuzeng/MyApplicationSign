package com.example.wz1.ec.core.launcher.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * Created by wz on 2018/9/26.
 */

public class ImageHolderCreater implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
