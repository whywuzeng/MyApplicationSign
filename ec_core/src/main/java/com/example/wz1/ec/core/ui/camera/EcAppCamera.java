package com.example.wz1.ec.core.ui.camera;

import com.example.wz1.ec.core.delegate.CheckDelegate;

/**
 * Created by wz on 2018/10/8.
 */

public class EcAppCamera {

    public static void start(CheckDelegate delegate){
        new CameraHandle(delegate).beginDialog();
    }
}
