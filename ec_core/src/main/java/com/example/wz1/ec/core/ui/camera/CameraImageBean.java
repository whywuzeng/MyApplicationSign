package com.example.wz1.ec.core.ui.camera;

import android.net.Uri;

/**
 * Created by Administrator on 2018-10-09.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.ui.camera
 */

public class CameraImageBean {

    private Uri mUri;

    private static final class HOLDER {
        private static final CameraImageBean sIntance=new CameraImageBean();
    }

    public static CameraImageBean getInstance(){
        return HOLDER.sIntance;
    }

    public void setUri(Uri uri){
        this.mUri=uri;
    }

    public Uri getUri()
    {
        return mUri;
    }
}
