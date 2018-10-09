package com.example.wz1.ec.core.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * Created by Administrator on 2018-10-09.
 * <p>
 * by author wz
 * <p>
 * com.example.wz1.ec.core.ui.camera
 */

public class RequestCodes {
    public static final int TAKEPHOTO = 0x1234;
    public static final int PICKPHOTO = 0x1231;
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
}
