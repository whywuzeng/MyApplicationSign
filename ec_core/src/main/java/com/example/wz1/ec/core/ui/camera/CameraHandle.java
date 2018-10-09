package com.example.wz1.ec.core.ui.camera;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.wz1.ec.core.R;
import com.example.wz1.ec.core.delegate.CheckDelegate;
import com.example.wz1.ec.core.utils.file.FileUtil;

import java.io.File;

/**
 * Created by wz on 2018/10/8.
 */

public class CameraHandle implements View.OnClickListener{

    private  final CheckDelegate DELEGATE ;
    private final AlertDialog dialog;

    public CameraHandle(CheckDelegate delegate){
        this.DELEGATE=delegate;
        dialog = new AlertDialog.Builder(delegate.getContext()).create();
    }

    public final void beginDialog(){
        dialog.show();
        Window window = dialog.getWindow();
        if (window!=null)
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

    private String getPhotoName(){
        return FileUtil.getFileNameByTime("IMG","jpg");
    }

    private void takePhoto() {
        String photoName = getPhotoName();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final File file = new File(FileUtil.CAMERA_PHOTO_DIR, photoName);
        final Uri fileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        //这里为什么要用一个单例来传递Uri ，不能直接放到intent？
        CameraImageBean.getInstance().setUri(fileUri);
        DELEGATE.startActivityForResult(intent, RequestCodes.TAKEPHOTO);
    }

    private void pickPhoto(){
        final Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        DELEGATE.startActivityForResult(Intent.createChooser(intent,"选取图片的方式"),RequestCodes.PICKPHOTO);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_loading_takePhoto) {
            takePhoto();
            dialog.cancel();
        }
        else if (i == R.id.btn_photo_native) {
            pickPhoto();
            dialog.cancel();
        }
        else if (i == R.id.btn_photo_cancel) {
        }
        else {
        }
    }
}
