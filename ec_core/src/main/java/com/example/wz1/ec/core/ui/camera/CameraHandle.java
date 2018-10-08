package com.example.wz1.ec.core.ui.camera;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.wz1.ec.core.R;
import com.example.wz1.ec.core.delegate.CheckDelegate;

/**
 * Created by wz on 2018/10/8.
 */

public class CameraHandle implements View.OnClickListener{

    private final AlertDialog dialog;

    public CameraHandle(CheckDelegate delegate){
         dialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public final void beginDialog(){
        dialog.show();
        Window window = dialog.getWindow();
        if (window==null)
        {
            window.setContentView(R.layout.dialog_camera_panel);
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.anim_panel_up_from_bottom);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width=WindowManager.LayoutParams.MATCH_PARENT;

            attributes.flags=WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            attributes.dimAmount=0.5f;
            window.setAttributes(attributes);

            window.findViewById(R.id.btn_loading_takePhoto).setOnClickListener(this);
            window.findViewById(R.id.btn_photo_native).setOnClickListener(this);
            window.findViewById(R.id.btn_photo_cancel).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_loading_takePhoto) {

        }
        else if (i == R.id.btn_photo_native) {
        }
        else if (i == R.id.btn_photo_cancel) {
        }
        else {
        }
    }
}
