package com.example.wz1.ec.core.delegate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wz1.ec.core.ui.camera.EcAppCamera;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 检查6.0的权限操作 delegate
 * Created by wz on 2018/9/1.
 */
@RuntimePermissions
public abstract class CheckDelegate extends BaseDelegate {

    @NeedsPermission({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE})
    void startCamera(){
        EcAppCamera.start(this);
    }

    @SuppressLint("NoCorrespondingNeedsPermission")
    @OnShowRationale(Manifest.permission.CAMERA)
    void onCameraRationale(final PermissionRequest request){
        showRationaleDialog(request);
    }

    public void checkCamera(){
        CheckDelegatePermissionsDispatcher.startCameraWithPermissionCheck(this);
    }

    protected  void showRationaleDialog(final PermissionRequest request){
        new AlertDialog.Builder(_mActivity).setPositiveButton("同意使用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.proceed();
            }
        }).setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).setCancelable(false)
                .setMessage("权限管理")
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CheckDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void onCameraDenied(){
        ToastUtils.showShort("不允许拍照");
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNever(){
        ToastUtils.showShort("永久拒绝使用拍照");
    }
}
