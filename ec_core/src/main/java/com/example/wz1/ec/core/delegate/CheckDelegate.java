package com.example.wz1.ec.core.delegate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.example.wz1.ec.core.ui.camera.CameraImageBean;
import com.example.wz1.ec.core.ui.camera.EcAppCamera;
import com.example.wz1.ec.core.ui.camera.RequestCodes;
import com.example.wz1.ec.core.ui.scanner.ScannerDelegate;
import com.example.wz1.ec.core.utils.callback.CallBackListener;
import com.example.wz1.ec.core.utils.callback.CallBackManager;
import com.example.wz1.ec.core.utils.callback.CallBackType;
import com.yalantis.ucrop.UCrop;

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
   public void startCamera(){
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

    @NeedsPermission(Manifest.permission.CAMERA)
    public void startScanner(BaseDelegate delegate){
        delegate.getSupportDelegate().startForResult(new ScannerDelegate(),RequestCodes.SCAN);
    }

    public void checkScanner(BaseDelegate delegate){
        CheckDelegatePermissionsDispatcher.startScannerWithPermissionCheck(this,delegate);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            if (requestCode == RequestCodes.TAKEPHOTO)
            {
                final Uri uri = CameraImageBean.getInstance().getUri();
                UCrop.of(uri,uri)
                        .withMaxResultSize(400,400)
                        .start(getContext(),this);
            }else if (requestCode == RequestCodes.CROP_PHOTO)
            {
                if (data!=null)
                {
                    final Uri resultUcrop = UCrop.getOutput(data);
                    final CallBackListener callBack = CallBackManager.getInstances().getCallBack(CallBackType.ON_CROP);
                    if (callBack!=null)
                    {
                        callBack.execute(resultUcrop);
                    }
                }

            }else if (requestCode == RequestCodes.CROP_ERROR)
            {
                 Throwable error=null;
                if (data!=null) {
                    error = UCrop.getError(data);
                }
                ToastUtils.showShort("裁剪出错:"+error.getLocalizedMessage());
            }else if (requestCode == RequestCodes.SCAN)
            {

            }else if (requestCode ==RequestCodes.PICKPHOTO)
            {
                //这个和 RequestCodes.CROP_PHOTO是一样的
                if (data!=null)
                {
                    final Uri resultUcrop = UCrop.getOutput(data);
                    final CallBackListener callBack = CallBackManager.getInstances().getCallBack(CallBackType.ON_CROP);
                    if (callBack!=null)
                    {
                        callBack.execute(resultUcrop);
                    }
                }
            }
        }
    }
}
