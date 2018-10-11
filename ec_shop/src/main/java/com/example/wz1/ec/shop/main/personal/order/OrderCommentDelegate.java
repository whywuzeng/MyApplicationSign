package com.example.wz1.ec.shop.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.wz1.ec.core.delegate.ECAppDelegate;
import com.example.wz1.ec.core.ui.widget.AutoPhotoLayout;
import com.example.wz1.ec.core.ui.widget.StarLayout;
import com.example.wz1.ec.core.utils.callback.CallBackListener;
import com.example.wz1.ec.core.utils.callback.CallBackManager;
import com.example.wz1.ec.core.utils.callback.CallBackType;
import com.example.wz1.ec.shop.R;
import com.example.wz1.ec.shop.R2;

import butterknife.BindView;

/**
 * Created by Administrator on 2018-10-11.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.shop.main.personal.order
 */

public class OrderCommentDelegate extends ECAppDelegate {
    @BindView(R2.id.star_order_comment)
    StarLayout starOrderComment;
    @BindView(R2.id.autophoto_layout)
    AutoPhotoLayout autophotoLayout;

    @Override
    public Object setLayout() {
        return R.layout.delegate_comment_layout;
    }

    @Override
    public void BindView(@Nullable Bundle savedInstanceState) {
        autophotoLayout.setDelegate(this);
        CallBackManager.getInstances().addCallBack(CallBackType.ON_CROP, new CallBackListener<Uri>() {
            @Override
            public void execute(Uri path) {
                autophotoLayout.onCropTarget(path);
            }
        });
    }

}
